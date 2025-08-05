package com.nuclei.assignment3.service.strategy;

import com.nuclei.assignment3.entity.Item;
import com.nuclei.assignment3.enums.ItemType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ManufacturedTaxCalculatorTest {

  private ManufacturedTaxCalculator calculator;

  @BeforeEach
  void setUp() {
    calculator = new ManufacturedTaxCalculator();
  }

  @Test
  @DisplayName("Should calculate correct tax for a standard item price")
  void testCalculateManufacturedTax_StandardPrice() {
    // Arrange
    Item item = Item.builder().price(100.0).type(ItemType.MANUFACTURED).build();
    double expectedTax = 14.75; // (100 * 0.125) + ((100 + 12.5) * 0.02)

    // Act
    double actualTax = calculator.calculate(item);

    // Assert
    assertEquals(expectedTax, actualTax, 0.001);
  }

  @Test
  @DisplayName("Should return zero tax for an item with zero price")
  void testCalculateManufacturedTax_ZeroPrice() {
    // Arrange: Edge case with price = 0
    Item item = Item.builder().price(0.0).type(ItemType.MANUFACTURED).build();
    double expectedTax = 0.0;

    // Act
    double actualTax = calculator.calculate(item);

    // Assert
    assertEquals(expectedTax, actualTax, 0.001);
  }
}