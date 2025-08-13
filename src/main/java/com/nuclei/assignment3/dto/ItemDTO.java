package com.nuclei.assignment3.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ItemDTO {
  private String name;
  private double itemPrice;
  private double salesTax;
  private double finalPrice;

  @Override
  public String toString() {
    return String.format(
        "| %-20s | Price: %9.2f | Tax: %9.2f | Final Price: %11.2f |",
        name, itemPrice, salesTax, finalPrice
    );
  }
}