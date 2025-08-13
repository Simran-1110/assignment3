package com.nuclei.assignment3.repository;

import com.nuclei.assignment3.constants.AppConstants;
import com.nuclei.assignment3.entity.Item;
import com.nuclei.assignment3.mapper.ItemResultSetMapper; // Import the new mapper
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

  public List<Item> fetchAllItems() {
    log.info("Fetching all items from the database using MapStruct mapper...");
    final String sql = AppConstants.QueryConstants.SELECT_ALL_ITEMS;

    return jdbcTemplate.query(sql, (rs, rowNum) -> ItemResultSetMapper.INSTANCE.map(rs));
  }
}