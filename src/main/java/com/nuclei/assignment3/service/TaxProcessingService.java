package com.nuclei.assignment3.service;

import com.nuclei.assignment3.dto.ItemDTO;
import com.nuclei.assignment3.entity.Item;
import com.nuclei.assignment3.mapper.ItemMapper;
import com.nuclei.assignment3.repository.ItemRepository;
import com.nuclei.assignment3.service.factory.TaxCalculatorFactory;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class TaxProcessingService {

  private final ItemRepository itemRepository;
  private final TaxCalculatorFactory taxCalculatorFactory;
  private final ExecutorService executorService;

  /**
   * Orchestrates the entire multithreaded processing workflow.
   */
  public List<ItemDTO> processAndCalculateTaxes() {
    final BlockingQueue<Item> itemQueue = new LinkedBlockingQueue<>();
    final ConcurrentLinkedQueue<Item> processedItems = new ConcurrentLinkedQueue<>();

    // Producer Task
    final CompletableFuture<Void> producer = CompletableFuture.runAsync(() ->
        runProducer(itemQueue), executorService);
    // Consumer Task
    final CompletableFuture<Void> consumer = CompletableFuture.runAsync(() ->
        runConsumer(itemQueue, processedItems), executorService);

    // Wait for both tasks to complete
    log.info("Waiting for producer and consumer tasks to complete...");
    CompletableFuture.allOf(producer, consumer).join();
    log.info("All processing tasks are finished.");

    return processedItems.stream().map(ItemMapper.INSTANCE::toDto).collect(Collectors.toList());
  }

  private void runProducer(final BlockingQueue<Item> queue) {
    log.info("[PRODUCER] Starting database read.");
    try {
      itemRepository.fetchAllItems().forEach(item -> {
        try {
          queue.put(item);
        } catch (InterruptedException e) {
          Thread.currentThread().interrupt();
          log.warn("[PRODUCER] Interrupted while queueing item.");
        }
      });
    } finally {
      try {
        // The "Poison Pill": a special object to signal the end of work
        queue.put(Item.builder().id(-1).build());
        log.info("[PRODUCER] Finished. Poison pill placed in queue.");
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
    }
  }

  private void runConsumer(
      final BlockingQueue<Item> queue,
      final ConcurrentLinkedQueue<Item> results
  ) {
    log.info("[CONSUMER] Starting to process items.");
    while (true) {
      try {
        final Item item = queue.take();

        // Check for the poison pill
        if (item.getId() == -1) {
          log.info("[CONSUMER] Poison pill detected. Shutting down.");
          break;
        }

        log.debug("[CONSUMER] Processing item: {}", item.getName());
        taxCalculatorFactory.getStrategy(item.getType())
            .ifPresentOrElse(
                strategy -> {
                  final double tax = strategy.calculate(item);
                  item.setTax(tax);
                  item.setFinalPrice(item.getPrice() + tax);
                  results.add(item);
                },
                () -> log.warn("No tax strategy found for item type: {}", item.getType())
          );
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
        log.error("[CONSUMER] Consumer thread was interrupted.");
        break;
      }
    }
  }
}