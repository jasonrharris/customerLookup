package com.jasonharris.customerlookup.dao;

import com.jasonharris.customerlookup.api.Customer;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.jdbi.v3.sqlobject.statement.UseRowMapper;

import java.util.Set;

public interface CustomerDAO {
    @SqlUpdate("create table Customer (id bigint auto_increment primary key, surname varchar(50), firstName varchar(50), phoneNumber varchar(25) )")
    void createTable();

    @SqlUpdate("insert into Customer (surname, firstName, phoneNumber) values (:surname, :firstName, :phoneNumber)")
    void insert(@Bind("surname") String surname, @Bind("firstName") String firstName, @Bind("phoneNumber") String phoneNumber);

    @SqlQuery("select * from Customer where surname = :surname")
    @UseRowMapper(CustomerRowMapper.class)
    Set<Customer> findAllBySurname(@Bind("surname") String surname);
}
