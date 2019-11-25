package com.jasonharris.customerlookup.dao;

import com.codahale.metrics.MetricRegistry;
import com.jasonharris.customerlookup.CustomerLookupApplication;
import com.jasonharris.customerlookup.api.Customer;
import io.dropwizard.db.DataSourceFactory;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;
import org.junit.jupiter.api.*;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CustomerDAOIntegrationTest {

    private static final String SURNAME_APPEARING_MANY_TIMES = "Harris";
    private static final String SURNAME_APPEARING_ONCE = "Smith";
    private static final String SURNAME_NOT_IN_DB = "Jones";
    private CustomerDAO customerDAO;

    @BeforeAll
    void setUp() {
        Jdbi jdbi = Jdbi.create(getDataSourceFactory().build(new MetricRegistry(), "test"));
        jdbi.installPlugin(new SqlObjectPlugin());
        customerDAO = CustomerLookupApplication.initialiseDB(jdbi);
    }

    @Test
    @Order(1)
    void shouldInsertCustomers() {
        customerDAO.insert(SURNAME_APPEARING_MANY_TIMES, "Jason", "0878 878999");
        customerDAO.insert(SURNAME_APPEARING_MANY_TIMES, "Robert", "0878 783859");
        customerDAO.insert(SURNAME_APPEARING_ONCE, "Robert", "01278 78319");
    }

    @Test
    @Order(2)
    void shouldFindAllCustomersNamedHarris() {
        Set<Customer> customers = customerDAO.findAllBySurname(SURNAME_APPEARING_MANY_TIMES);

        assertTrue((customers.size() == 2));

        for (Customer customer : customers) {
            if (!customer.getSurname().equals(SURNAME_APPEARING_MANY_TIMES)) {
                throwWrongSurnameException(SURNAME_APPEARING_MANY_TIMES);
                return;
            }
        }
    }

    @Test
    @Order(2)
    void shouldNotFindCustomersWithNoEntryInDB() {
        Set<Customer> customers = customerDAO.findAllBySurname(SURNAME_NOT_IN_DB);

        assertTrue(customers.isEmpty());
    }

    @Test
    @Order(2)
    void shouldFindCustomerWithSingleEntryInDB() {
        Set<Customer> customers = customerDAO.findAllBySurname(SURNAME_APPEARING_ONCE);

        assertTrue((customers.size() == 1));

        if (!customers.iterator().next().getSurname().equals(SURNAME_APPEARING_ONCE)) {
            throwWrongSurnameException(SURNAME_APPEARING_ONCE);
        }
    }

    private DataSourceFactory getDataSourceFactory() {
        DataSourceFactory dataSourceFactory = new DataSourceFactory();
        dataSourceFactory.setDriverClass("org.h2.Driver");
        dataSourceFactory.setUrl("jdbc:h2:mem:test");
        dataSourceFactory.setUser("sa");
        dataSourceFactory.setPassword("");
        return dataSourceFactory;
    }

    private void throwWrongSurnameException(String surnameAppearingManyTimes) {
        throw new AssertionError("Only Rows with surname " + surnameAppearingManyTimes + " should have been found");
    }
}
