package com.nuclei.assignment3.service.strategy;

import com.nuclei.assignment3.entity.Item;
import com.nuclei.assignment3.enums.ItemType;
import org.springframework.stereotype.Component;

@Component
public class ManufacturedTaxCalculator implements TaxCalculatorStrategy {

  private static final double BASE_TAX_RATE = 0.125; // 12.5%
  private static final double ADDITIONAL_TAX_RATE = 0.02; // 2%

  @Override
  public double calculate(final Item item) {
    final double baseTax = item.getPrice() * BASE_TAX_RATE;
    final double taxableAmountForAdditional = item.getPrice() + baseTax;
    return baseTax + (taxableAmountForAdditional * ADDITIONAL_TAX_RATE);
  }

  @Override
  public ItemType getStrategyType() {
    return ItemType.MANUFACTURED;
  }
}