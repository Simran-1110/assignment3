package com.nuclei.assignment3.runner;

import com.nuclei.assignment3.constants.AppConstants;
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
    log.info(AppConstants.LogConstants.JOB_START);
    try {
      final long startTime = System.currentTimeMillis();
      final List<ItemDTO> results = taxProcessingService.processAndCalculateTaxes();
      final long duration = System.currentTimeMillis() - startTime;

      printResults(results, duration);
    } catch (Exception e) {
      log.error(AppConstants.LogConstants.JOB_ERROR, e);
    }
    log.info(AppConstants.LogConstants.JOB_END);
  }

  private void printResults(final List<ItemDTO> results, final long duration) {
    final StringBuilder report = new StringBuilder();

    report.append(AppConstants.LogConstants.REPORT_HEADER)
        .append(AppConstants.LogConstants.REPORT_TABLE_BORDER);

    results.forEach(item -> report.append("\n").append(item.toString()));

    report.append(AppConstants.LogConstants.REPORT_FOOTER_BORDER)
        .append(String.format(AppConstants.LogConstants.REPORT_SUMMARY_FORMAT,
            results.size(), duration))
        .append(AppConstants.LogConstants.REPORT_SUMMARY_LINE);

    log.info(report.toString());
  }
}