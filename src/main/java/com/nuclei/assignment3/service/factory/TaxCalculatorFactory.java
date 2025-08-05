package com.nuclei.assignment3.service.factory;

import com.nuclei.assignment3.enums.ItemType;
import com.nuclei.assignment3.service.strategy.TaxCalculatorStrategy;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class TaxCalculatorFactory {

  private final List<TaxCalculatorStrategy> strategies;
  private final Map<ItemType, TaxCalculatorStrategy> strategyCache = new EnumMap<>(ItemType.class);

  // Spring injects all beans that implement the TaxCalculatorStrategy interface
  public TaxCalculatorFactory(final List<TaxCalculatorStrategy> strategies) {
    this.strategies = strategies;
  }

  /**
   * This method populates the cache after dependencies are injected.
   */
  @PostConstruct
  public void initStrategies() {
    strategies.forEach(strategy -> strategyCache.put(strategy.getStrategyType(), strategy));
  }

  public Optional<TaxCalculatorStrategy> getStrategy(final ItemType type) {
    return Optional.ofNullable(strategyCache.get(type));
  }
}