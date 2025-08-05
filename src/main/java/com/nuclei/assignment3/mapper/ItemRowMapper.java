package com.nuclei.assignment3.mapper;

import com.nuclei.assignment3.entity.Item;
import com.nuclei.assignment3.enums.ItemType;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;

import org.springframework.jdbc.core.RowMapper;

public class ItemRowMapper implements RowMapper<Item> {

  @Override
  public Item mapRow(final ResultSet rs, final int rowNum) throws SQLException {
    return Item.builder()
        .id(rs.getInt("id"))
        .name(rs.getString("name"))
        .price(rs.getDouble("price"))
        .quantity(rs.getInt("quantity"))
        .type(ItemType.valueOf(rs.getString("type").toUpperCase(Locale.ROOT)))
        .createdAt(rs.getTimestamp("created_at").toLocalDateTime())
        .updatedAt(rs.getTimestamp("updated_at").toLocalDateTime())
        .build();
  }
}