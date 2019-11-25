package com.jasonharris.customerlookup.dao;

import com.jasonharris.customerlookup.api.Customer;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;

public interface CustomerDAO {
    @SqlUpdate("create table Customer (id int primary key, surname varchar(50), firstName varchar(50), phoneNumber varchar(20) )")
    void createTable();

    @SqlUpdate("insert into Customer (id, surname, firstName, :phoneNumber) values (:id, :surname, :firstName, :phoneNumber)")
    void insert(@Bind("id") int id, @Bind("surname") String surname, @Bind("firstName") String firstName, @Bind("phoneNumber") String phoneNumber);

    @SqlQuery("select * from Customer where surname = :surname")
    List<Customer> findAllBySurname(@Bind("surname") String surname);
}
