package com.cake7.database.model.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public interface JdbcRepository<T, ID> {
    Logger logger = LoggerFactory.getLogger(JdbcRepository.class);

    DataSource getDataSource();
    String getTableName();

    default Optional<T> findById(ID id, String tableName, RowMapper<T> rowMapper) throws SQLException {
        String sql = "SELECT * FROM " + tableName + " WHERE id = ?";
        try(Connection conn = getDataSource().getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setObject(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(rowMapper.mapRow(rs, 1));
                }
            }
        } catch (SQLException e) {
            logger.error("SQL Exception: " + e.getMessage());
        }
        return Optional.empty();
    }
}
