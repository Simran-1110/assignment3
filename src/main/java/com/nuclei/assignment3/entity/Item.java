package com.nuclei.assignment3.entity;

import com.nuclei.assignment3.enums.ItemType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@NoArgsConstructor
@ToString(callSuper = true)
public class Item extends Auditable {
  private int id;
  private String name;
  private double price;
  private int quantity;
  private ItemType type;
  private double tax;
  private double finalPrice;
}