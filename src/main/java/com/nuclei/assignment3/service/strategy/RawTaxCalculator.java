package com.nuclei.assignment3.service.strategy;

import com.nuclei.assignment3.constants.AppConstants;
import com.nuclei.assignment3.entity.Item;
import com.nuclei.assignment3.enums.ItemType;
import org.springframework.stereotype.Component;

@Component
public class RawTaxCalculator implements TaxCalculatorStrategy {

  @Override
  public double calculate(final Item item) {

    return item.getPrice() * AppConstants.TaxRuleConstants.RAW_TAX_RATE;
  }

  @Override
  public ItemType getStrategyType() {
    return ItemType.RAW;
  }
}