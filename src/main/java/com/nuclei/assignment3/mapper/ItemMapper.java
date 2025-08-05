package com.nuclei.assignment3.mapper;

import com.nuclei.assignment3.dto.ItemDTO;
import com.nuclei.assignment3.entity.Item;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring") // Tells MapStruct to create a Spring Bean implementation
public interface ItemMapper {

  // A singleton instance for easy access without DI
  ItemMapper INSTANCE = Mappers.getMapper(ItemMapper.class);

  /**
   * Maps an Item entity to an ItemDTO.
   * @param item The source Item entity.
   * @return The mapped ItemDTO.
   */
  @Mapping(source = "price", target = "itemPrice") // Maps Item.price to ItemDTO.itemPrice
  @Mapping(source = "tax", target = "salesTax")     // Maps Item.tax to ItemDTO.salesTax
  ItemDTO toDto(Item item);
}