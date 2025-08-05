package com.nuclei.assignment3.service.strategy;

import com.nuclei.assignment3.entity.Item;
import com.nuclei.assignment3.enums.ItemType;
import org.springframework.stereotype.Component;

@Component
public class RawTaxCalculator implements TaxCalculatorStrategy {

  private static final double TAX_RATE = 0.125; // 12.5%

  @Override
  public double calculate(final Item item) {
    return item.getPrice() * TAX_RATE;
  }

  @Override
  public ItemType getStrategyType() {
    return ItemType.RAW;
  }
}