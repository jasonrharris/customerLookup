package com.jasonharris.customerlookup.resources;

import com.jasonharris.customerlookup.api.Address;
import com.jasonharris.customerlookup.api.Customer;
import com.jasonharris.customerlookup.dao.CustomerDAO;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CustomerSearchResourceTest {

    private static final String TEST_SURNAME = "TestSurname";


    private CustomerSearchResource customerSearchResource;

    @Mock
    private CustomerDAO customerDAO;


    @BeforeAll
    void setUp(){
        MockitoAnnotations.initMocks(this);
        customerSearchResource = new CustomerSearchResource(customerDAO);
    }

    @Test
    void shouldFindCustomersIfSurnameExistsInPersistence() {
        Address address1 = new Address("20 Hillsden Street", "", "Pullbourough", "GN6 7TY");
        Customer firstCustomer = new Customer("First","Surname","07814 898776", address1);

        Address address2 = new Address("101 Top Down", "Hillsville", "Rochdale", "RN67 131");
        Customer secondCustomer = new Customer("Second","Surname","03714 818371", address2);

        when(customerDAO.findAllBySurname(TEST_SURNAME)).thenReturn(Arrays.asList(firstCustomer, secondCustomer));

        List<Customer> matchedCustomers = customerSearchResource.findCustomers(TEST_SURNAME);

        assertFalse(matchedCustomers.isEmpty());

        assertEquals(matchedCustomers.get(0), firstCustomer);

        assertEquals(matchedCustomers.get(1), secondCustomer);
    }

    @Test
    void shouldNotFindCustomersIfSurnameDoesNotExistInPersistence() {

        when(customerDAO.findAllBySurname(TEST_SURNAME)).thenReturn(Collections.emptyList());

        List<Customer> matchedCustomers = customerSearchResource.findCustomers(TEST_SURNAME);

        assertTrue(matchedCustomers.isEmpty());
    }
}
