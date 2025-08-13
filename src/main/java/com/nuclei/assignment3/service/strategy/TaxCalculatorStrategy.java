package com.nuclei.assignment3.service.strategy;

import com.nuclei.assignment3.entity.Item;
import com.nuclei.assignment3.enums.ItemType;

public interface TaxCalculatorStrategy {

  double calculate(Item item);

  ItemType getStrategyType();
}