package com.cake7.database.model.repository;

import com.cake7.database.domain.Users;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Optional;
import java.util.UUID;

@Repository
public class UserRepository implements JdbcRepository<Users, UUID> {

    private final DataSource dataSource;
    private final RowMapper<Users> rowMapper = (rs, rowNum) -> new Users(
            UUID.fromString(rs.getString("id")),
            rs.getString("name"),
            rs.getString("email")
    );

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

    public Optional<Users> findUserById(UUID id) throws SQLException {
        return findById(id, getTableName(), rowMapper);
    }

}
