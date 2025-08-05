package com.nuclei.assignment3.service.strategy;

import com.nuclei.assignment3.entity.Item;
import com.nuclei.assignment3.enums.ItemType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ImportedTaxCalculatorTest {

  private ImportedTaxCalculator calculator;

  @BeforeEach
  void setUp() {
    calculator = new ImportedTaxCalculator();
  }

  @DisplayName("Should calculate correct tax across all surcharge brackets")
  @ParameterizedTest(name = "For price {0}, expected tax is {1}")
  @CsvSource({
      "80.0, 13.0",   // Cost+duty = 88 (< 100). Tax = (80*0.1) + 5 = 13
      "150.0, 25.0",  // Cost+duty = 165 (> 100, < 200). Tax = (150*0.1) + 10 = 25
      "200.0, 31.0"   // Cost+duty = 220 (> 200). Tax = (200*0.1) + (220*0.05) = 20 + 11 = 31
  })
  void testCalculateImportedTax_WithSurchargeBrackets(double price, double expectedTax) {
    // Arrange
    Item item = Item.builder().price(price).type(ItemType.IMPORTED).build();

    // Act
    double actualTax = calculator.calculate(item);

    // Assert
    assertEquals(expectedTax, actualTax, 0.001);
  }

  @Test
  @DisplayName("Should apply minimum surcharge for an item with zero price")
  void testCalculateImportedTax_ZeroPrice() {
    // Arrange: Edge case for a zero-price item
    Item item = Item.builder().price(0.0).type(ItemType.IMPORTED).build();
    // 10% duty = 0. Cost+duty = 0. Surcharge for cost <= 100 is 5.
    double expectedTax = 5.0;

    // Act
    double actualTax = calculator.calculate(item);

    // Assert
    assertEquals(expectedTax, actualTax, 0.001);
  }
}