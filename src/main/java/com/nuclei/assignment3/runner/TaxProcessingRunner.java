package com.nuclei.assignment3.runner;

import com.nuclei.assignment3.dto.ItemDTO;
import com.nuclei.assignment3.service.TaxProcessingService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class TaxProcessingRunner implements CommandLineRunner {

  private final TaxProcessingService taxProcessingService;

  @Override
  public void run(final String... args) {
    log.info("========== Starting Item Tax Processing Job ==========");
    try {
      final long startTime = System.currentTimeMillis();
      final List<ItemDTO> results = taxProcessingService.processAndCalculateTaxes();
      final long duration = System.currentTimeMillis() - startTime;

      printResults(results, duration);
    } catch (Exception e) {
      log.error("A critical error occurred during the tax processing job.", e);
    }
    log.info("========== Item Tax Processing Job Finished ==========");
  }

  private void printResults(final List<ItemDTO> results, final long duration) {
    final StringBuilder report = new StringBuilder();

    // Chain the .append() calls to satisfy static analysis and improve style
    report.append("\n----------------------- TAX CALCULATION RESULTS -----------------------")
        .append("\n+----------------------+-----------------------------------------------+");

    results.forEach(item -> report.append("\n").append(item.toString()));

    report.append("\n+----------------------------------------------------------------------+")
        .append(String.format("\nSuccessfully processed %d items in %d milliseconds.",
            results.size(), duration))
        .append("\n-------------------------------------------------------------------");

    log.info(report.toString());
  }
}