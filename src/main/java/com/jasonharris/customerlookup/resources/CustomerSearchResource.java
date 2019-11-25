package com.jasonharris.customerlookup.resources;

import com.jasonharris.customerlookup.api.Customer;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.Collections;
import java.util.List;

@Path("/customer")
@Produces(MediaType.APPLICATION_JSON)
public class CustomerSearchResource {

    @GET
    @Path("search")
    public List<Customer> findCustomers(@QueryParam("surname") String surname){
        return Collections.emptyList();
    }
}
