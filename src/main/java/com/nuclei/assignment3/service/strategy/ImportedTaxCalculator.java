package com.nuclei.assignment3.service.strategy;

import com.nuclei.assignment3.entity.Item;
import com.nuclei.assignment3.enums.ItemType;
import org.springframework.stereotype.Component;

@Component
public class ImportedTaxCalculator implements TaxCalculatorStrategy {

  private static final double IMPORT_DUTY_RATE = 0.10; // 10%
  private static final double FIRST_LIMIT = 100;
  private static final double SECOND_LIMIT = 200;

  @Override
  public double calculate(final Item item) {
    final double importDuty = item.getPrice() * IMPORT_DUTY_RATE;
    final double finalCostWithDuty = item.getPrice() + importDuty;
    double surcharge;

    if (finalCostWithDuty <= FIRST_LIMIT) {
      surcharge = 5;
    } else if (finalCostWithDuty <= SECOND_LIMIT) {
      surcharge = 10;
    } else {
      surcharge = finalCostWithDuty * 0.05; // 5% of the cost
    }

    return importDuty + surcharge;
  }

  @Override
  public ItemType getStrategyType() {
    return ItemType.IMPORTED;
  }
}