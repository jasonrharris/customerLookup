package com.jasonharris.customerlookup.resources;


import com.jasonharris.customerlookup.api.Customer;
import com.jasonharris.customerlookup.dao.CustomerDAO;
import io.dropwizard.testing.junit.ResourceTestRule;
import org.junit.After;
import org.junit.Before;
import org.junit.ClassRule;

import javax.ws.rs.core.GenericType;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;


public class CustomerSearchResourceIntegrationTest {

    private static final String SMITH = "smith";
    private static CustomerDAO customerDAO = mock(CustomerDAO.class);

    @ClassRule
    public static final ResourceTestRule resources = ResourceTestRule.builder()
            .addResource(new CustomerSearchResource(customerDAO))
            .build();

    private final Customer customer1 = new Customer(1, "Jenny", SMITH, "04535 848828");
    private final Customer customer2 = new Customer(2, "Ben", "jones", "04535 848828");

    @Before
    public void setup() {
        when(customerDAO.findAllBySurname(eq(SMITH))).thenReturn(Collections.singleton(customer1));
    }

    @After
    public void tearDown() {
        reset(customerDAO);
    }

    @org.junit.Test
    public void findCustomers() {
        Set<Customer> matchedContacts = resources.target("/customer/search").queryParam("surname", SMITH).request().get(new GenericType<>() {
        });
        assertThat(matchedContacts.size(), equalTo(1));
        assertThat(matchedContacts.iterator().next().getSurname(), equalTo(SMITH));
    }
}
