package com.jasonharris.customerlookup.resources;

import com.jasonharris.customerlookup.api.Customer;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CustomerSearchResourceTest {

    private static final String TEST_SURNAME = "TestSurname";
    private CustomerSearchResource customerSearchResource;

    @BeforeAll
    void setUp(){
        customerSearchResource = new CustomerSearchResource();
    }

    @Test
    void findCustomers() {
        List<Customer> matchedCustomers = customerSearchResource.findCustomers(TEST_SURNAME);
        assertFalse(matchedCustomers.isEmpty());
    }
}
