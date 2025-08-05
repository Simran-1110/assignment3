package com.nuclei.assignment3.repository;

import com.nuclei.assignment3.entity.Item;
import com.nuclei.assignment3.mapper.ItemRowMapper;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@RequiredArgsConstructor
public class ItemRepository {

  private final JdbcTemplate jdbcTemplate;

  /**
   * Fetches all items from the database.
   */
  public List<Item> fetchAllItems() {
    log.info("Fetching all items from the database...");
    final String sql = "SELECT id, name, price, quantity, type, created_at, updated_at FROM items";
    final List<Item> items = jdbcTemplate.query(sql, new ItemRowMapper());
    log.info("Successfully fetched {} items.", items.size());
    return items;
  }
}