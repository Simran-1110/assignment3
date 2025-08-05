package com.nuclei.assignment3.service.strategy;

import com.nuclei.assignment3.entity.Item;
import com.nuclei.assignment3.enums.ItemType;

public interface TaxCalculatorStrategy {
  /**
   * Calculates the tax for a given item.
   */
  double calculate(Item item);

  /**
   * A method to identify which ItemType this strategy handles.
   */
  ItemType getStrategyType();
}