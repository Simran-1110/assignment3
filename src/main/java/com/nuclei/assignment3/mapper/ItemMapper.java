package com.nuclei.assignment3.mapper;

import com.nuclei.assignment3.dto.ItemDTO;
import com.nuclei.assignment3.entity.Item;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ItemMapper {

  ItemMapper INSTANCE = Mappers.getMapper(ItemMapper.class);

  /**
   * Maps an Item entity to an ItemDTO.
   * @param item The source Item entity.
   * @return The mapped ItemDTO.
   */
  @Mapping(source = "price", target = "itemPrice")
  @Mapping(source = "tax", target = "salesTax")
  ItemDTO toDto(Item item);
}