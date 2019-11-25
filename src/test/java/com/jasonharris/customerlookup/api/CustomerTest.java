package com.jasonharris.customerlookup.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.dropwizard.jackson.Jackson;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {
    private static final ObjectMapper MAPPER = Jackson.newObjectMapper();

    @Test
    void serializesToJSON() throws Exception {
        final Customer customer = new Customer(1,"Luther", "Blissett", "0989 756789");

        assertEquals("{\"id\":1,\"firstName\":\"Luther\",\"surname\":\"Blissett\",\"phoneNumber\":\"0989 756789\"}", MAPPER.writeValueAsString(customer));
    }

    @Test
    void derializesFromJSON() throws Exception {

        final Customer customer = new Customer(1,"Luther", "Blissett", "0989 756789");

        assertEquals(customer, MAPPER.reader().forType(Customer.class).readValue("{\"id\":1,\"firstName\":\"Luther\",\"surname\":\"Blissett\",\"phoneNumber\":\"0989 756789\"}"));
    }

}
