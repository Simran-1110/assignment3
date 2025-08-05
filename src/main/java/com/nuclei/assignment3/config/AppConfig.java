package com.nuclei.assignment3.config;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

  /**
   * Defines a fixed-size thread pool as a Spring-managed bean.
   * We use 2 threads: one for the producer and one for the consumer.
   * The destroyMethod ensures the pool is shut down gracefully with the application.
   */
  @Bean(destroyMethod = "shutdown")
  public ExecutorService taskExecutor() {
    return Executors.newFixedThreadPool(2);
  }
}