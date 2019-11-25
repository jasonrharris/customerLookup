package com.jasonharris.customerlookup.resources;

import com.jasonharris.customerlookup.api.Customer;
import com.jasonharris.customerlookup.dao.CustomerDAO;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Path("/customer")
@Produces(MediaType.APPLICATION_JSON)
public class CustomerSearchResource {

    private final CustomerDAO customerDAO;

    public CustomerSearchResource(CustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
    }

    @GET
    @Path("search")
    public Set<Customer> findCustomers(@QueryParam("surname") String surname){
        return customerDAO.findAllBySurname(surname);
    }
}
