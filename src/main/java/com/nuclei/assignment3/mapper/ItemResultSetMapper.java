package com.nuclei.assignment3.mapper;

import com.nuclei.assignment3.entity.Item;
import com.nuclei.assignment3.enums.ItemType;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(imports = ItemType.class)
public interface ItemResultSetMapper {

  ItemResultSetMapper INSTANCE = Mappers.getMapper(ItemResultSetMapper.class);

  /**
   * Maps a ResultSet's current row to an Item entity using explicit expressions.
   */
  @Mapping(target = "id", expression = "java(rs.getInt(\"id\"))")
  @Mapping(target = "name", expression = "java(rs.getString(\"name\"))")
  @Mapping(target = "price", expression = "java(rs.getDouble(\"price\"))")
  @Mapping(target = "quantity", expression = "java(rs.getInt(\"quantity\"))")
  @Mapping(target = "type",
      expression = "java(ItemType.valueOf(rs.getString(\"type\").toUpperCase()))")
  @Mapping(target = "createdAt",
      expression = "java(rs.getTimestamp(\"created_at\").toLocalDateTime())")
  @Mapping(target = "updatedAt",
      expression = "java(rs.getTimestamp(\"updated_at\").toLocalDateTime())")
  Item map(ResultSet rs) throws SQLException;
}