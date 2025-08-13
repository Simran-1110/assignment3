package com.nuclei.assignment3.service.strategy;

import com.nuclei.assignment3.constants.AppConstants;
import com.nuclei.assignment3.entity.Item;
import com.nuclei.assignment3.enums.ItemType;
import org.springframework.stereotype.Component;

@Component
public class ManufacturedTaxCalculator implements TaxCalculatorStrategy {

  @Override
  public double calculate(final Item item) {
    final double baseTax =
        item.getPrice() * AppConstants.TaxRuleConstants.MANUFACTURED_BASE_TAX_RATE;
    final double taxableAmountForAdditional = item.getPrice() + baseTax;
    return baseTax + (taxableAmountForAdditional
        * AppConstants.TaxRuleConstants.MANUFACTURED_ADDITIONAL_TAX_RATE);
  }

  @Override
  public ItemType getStrategyType() {
    return ItemType.MANUFACTURED;
  }
}