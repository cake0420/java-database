package com.cake7.database.model.repository;

import com.cake7.database.domain.Users;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Repository
public class UserRepository implements JdbcRepository<Users, byte[]> {

    private final DataSource dataSource;

    public UserRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public DataSource getDataSource() {
        return dataSource;
    }

    @Override
    public String getTableName() {
        return "users";
    }

    public boolean existByEmail(String email) throws SQLException {
        String sql = "SELECT count(*) FROM " + getTableName() + " WHERE email = ? LIMIT 1";
        try(Connection conn = getDataSource().getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setObject(1, email);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            logger.error("SQL Exception: " + e.getMessage());
            throw new SQLException("쿼리 실행 중 오류 발생" +e.getMessage());

        } catch (DataAccessResourceFailureException e) {
            logger.error("DataAccessResourceFailureException: " + e.getMessage());
            throw new DataAccessResourceFailureException("데이터베이스 연결 또는 쿼리 실행 중 오류 발생" +e.getMessage());
        }
        return false;
    }

    @Override
    public Map<String, Object> entityToMap(Users user) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", user.getId());
        map.put("name", user.getName());
        map.put("email", user.getEmail());
        map.put("password", user.getPassword());
        map.put("salt", user.getSalt());
        return map;
    }

}
