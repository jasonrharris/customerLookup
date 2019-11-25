package com.jasonharris.customerlookup.dao;

import com.jasonharris.customerlookup.api.Customer;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerRowMapper implements RowMapper<Customer> {
    @Override
    public Customer map(ResultSet rs, StatementContext ctx) throws SQLException {
        return new Customer(rs.getInt("id"), rs.getString("firstName"), rs.getString("surname"), rs.getString("phoneNumber"));
    }
}
