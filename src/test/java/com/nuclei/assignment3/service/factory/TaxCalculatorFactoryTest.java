package com.nuclei.assignment3.service.factory;

import com.nuclei.assignment3.enums.ItemType;
import com.nuclei.assignment3.service.strategy.TaxCalculatorStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TaxCalculatorFactoryTest {

  @Mock
  private TaxCalculatorStrategy rawStrategy;
  @Mock
  private TaxCalculatorStrategy manufacturedStrategy;

  private TaxCalculatorFactory factory;

  @BeforeEach
  void setUp() {
    // This setup is for the "happy path" tests
    when(rawStrategy.getStrategyType()).thenReturn(ItemType.RAW);
    when(manufacturedStrategy.getStrategyType()).thenReturn(ItemType.MANUFACTURED);
    factory = new TaxCalculatorFactory(List.of(rawStrategy, manufacturedStrategy));
    factory.initStrategies();
  }

  @Test
  @DisplayName("Should return the correct strategy for a given type")
  void shouldReturnCorrectStrategy() {
    Optional<TaxCalculatorStrategy> strategy = factory.getStrategy(ItemType.RAW);
    assertTrue(strategy.isPresent());
    assertEquals(rawStrategy, strategy.get());
  }

  @Test
  @DisplayName("Should return empty optional for an unregistered type")
  void shouldReturnEmptyForUnknownType() {
    Optional<TaxCalculatorStrategy> strategy = factory.getStrategy(ItemType.IMPORTED);
    assertFalse(strategy.isPresent());
  }

  @Test
  @DisplayName("Should return empty optional when input type is null")
  void shouldReturnEmptyForNullType() {
    // Edge Case: test with null input
    Optional<TaxCalculatorStrategy> strategy = factory.getStrategy(null);
    assertFalse(strategy.isPresent());
  }

  @Test
  @DisplayName("Should handle initialization with an empty list of strategies")
  void shouldHandleEmptyStrategyList() {
    // Edge Case: test factory initialization with no strategies
    TaxCalculatorFactory emptyFactory = new TaxCalculatorFactory(Collections.emptyList());
    emptyFactory.initStrategies();
    Optional<TaxCalculatorStrategy> strategy = emptyFactory.getStrategy(ItemType.RAW);
    assertFalse(strategy.isPresent());
  }
}