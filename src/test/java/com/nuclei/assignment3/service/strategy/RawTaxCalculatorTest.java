package com.nuclei.assignment3.service.strategy;

import com.nuclei.assignment3.entity.Item;
import com.nuclei.assignment3.enums.ItemType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RawTaxCalculatorTest {

  private RawTaxCalculator calculator;

  @BeforeEach
  void setUp() {
    calculator = new RawTaxCalculator();
  }

  @Test
  @DisplayName("Should calculate correct tax for a standard item price")
  void testCalculateRawTax_StandardPrice() {
    // Arrange: Create a sample raw item with a standard price
    Item item = Item.builder().price(200.0).type(ItemType.RAW).build();
    double expectedTax = 25.0; // 12.5% of 200

    // Act: Calculate the tax
    double actualTax = calculator.calculate(item);

    // Assert: Check if the calculated tax is correct
    assertEquals(expectedTax, actualTax, 0.001);
  }

  @Test
  @DisplayName("Should return zero tax for an item with zero price")
  void testCalculateRawTax_ZeroPrice() {
    // Arrange: Edge case with price = 0
    Item item = Item.builder().price(0.0).type(ItemType.RAW).build();
    double expectedTax = 0.0;

    // Act
    double actualTax = calculator.calculate(item);

    // Assert
    assertEquals(expectedTax, actualTax, 0.001);
  }
}