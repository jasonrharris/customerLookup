package com.jasonharris.customerlookup.resources;

import com.jasonharris.customerlookup.api.Customer;
import com.jasonharris.customerlookup.dao.CustomerDAO;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CustomerSearchResourceTest {

    private static final String TEST_SURNAME = "TestSurname";

    private CustomerSearchResource customerSearchResource;

    @Mock
    private CustomerDAO customerDAO;

    @BeforeAll
    void setUp() {
        MockitoAnnotations.initMocks(this);
        customerSearchResource = new CustomerSearchResource(customerDAO);
    }

    @Test
    void shouldFindCustomersIfSurnameExistsInPersistence() {

        Customer firstCustomer = new Customer(1, "First", "Surname", "07814 898776");

        Customer secondCustomer = new Customer(2, "Second", "Surname", "03714 818371");

        when(customerDAO.findAllBySurname(TEST_SURNAME)).thenReturn(new LinkedHashSet<>(Arrays.asList(firstCustomer, secondCustomer)));

        Set<Customer> matchedCustomers = customerSearchResource.findCustomers(TEST_SURNAME);

        assertFalse(matchedCustomers.isEmpty());

        int elementIndex = 0;

        for (Customer matchedCustomer : matchedCustomers) {
            Customer expectedCustomer = elementIndex++ == 0 ? firstCustomer : secondCustomer;
            assertEquals(matchedCustomer, expectedCustomer);
        }
    }

    @Test
    void shouldNotFindCustomersIfSurnameDoesNotExistInPersistence() {

        when(customerDAO.findAllBySurname(TEST_SURNAME)).thenReturn(Collections.emptySet());

        Set<Customer> matchedCustomers = customerSearchResource.findCustomers(TEST_SURNAME);

        assertTrue(matchedCustomers.isEmpty());
    }
}
