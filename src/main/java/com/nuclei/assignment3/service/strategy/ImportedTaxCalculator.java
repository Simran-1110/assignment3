package com.nuclei.assignment3.service.strategy;

import com.nuclei.assignment3.constants.AppConstants;
import com.nuclei.assignment3.entity.Item;
import com.nuclei.assignment3.enums.ItemType;
import java.util.NavigableMap;
import java.util.TreeMap;
import java.util.function.Function;
import org.springframework.stereotype.Component;

@Component
public class ImportedTaxCalculator implements TaxCalculatorStrategy {

  private static final NavigableMap<Double, Function<Double, Double>> SURCHARGE_RULES =
      new TreeMap<>();

  static {
    // Bracket 1: For costs from 0.0 to 100.0
    SURCHARGE_RULES.put(
        0.0, finalCost -> AppConstants.TaxRuleConstants.IMPORTED_SURCHARGE_FLAT_RATE_1);

    // Bracket 2: For costs from 100.01 to 200.0
    SURCHARGE_RULES.put(AppConstants.TaxRuleConstants.IMPORTED_SURCHARGE_FIRST_LIMIT + 0.01,
        finalCost -> AppConstants.TaxRuleConstants.IMPORTED_SURCHARGE_FLAT_RATE_2);

    // Bracket 3: For costs from 200.01 and up
    SURCHARGE_RULES.put(AppConstants.TaxRuleConstants.IMPORTED_SURCHARGE_SECOND_LIMIT + 0.01,
        finalCost -> finalCost * AppConstants.TaxRuleConstants.IMPORTED_SURCHARGE_PERCENTAGE_RATE);
  }

  @Override
  public double calculate(final Item item) {
    final double importDuty = item.getPrice() * AppConstants.TaxRuleConstants.IMPORT_DUTY_RATE;
    final double finalCostWithDuty = item.getPrice() + importDuty;

    final double surcharge = calculateSurcharge(finalCostWithDuty);

    return importDuty + surcharge;
  }

  private double calculateSurcharge(final double finalCost) {

    final Function<Double, Double> rule = SURCHARGE_RULES.floorEntry(finalCost).getValue();
    return rule.apply(finalCost);
  }

  @Override
  public ItemType getStrategyType() {
    return ItemType.IMPORTED;
  }
}