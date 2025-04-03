package com.cake7.database.model.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessResourceFailureException;
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

    default Optional<T> findById(ID id,  RowMapper<T> rowMapper) throws Exception {
        String sql = "SELECT * FROM " + getTableName() + " WHERE id = ?";
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
            throw new SQLException("쿼리 실행 중 오류 발생" +e.getMessage());

        } catch (DataAccessResourceFailureException e) {
            logger.error("DataAccessResourceFailureException: " + e.getMessage());
            throw new DataAccessResourceFailureException("데이터베이스 연결 또는 쿼리 실행 중 오류 발생" +e.getMessage());
        } catch (Exception e) {
            logger.error("Exception: " + e.getMessage());
            throw new Exception("예상하지 못한 오류" + e.getMessage());
        }
        return Optional.empty();
    }
}
