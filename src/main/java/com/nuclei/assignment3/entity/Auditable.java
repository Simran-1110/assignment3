package com.nuclei.assignment3.entity;

import java.time.LocalDateTime;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;



@Data
@SuperBuilder
@NoArgsConstructor
public abstract class Auditable {
  protected LocalDateTime createdAt;
  protected LocalDateTime updatedAt;
}