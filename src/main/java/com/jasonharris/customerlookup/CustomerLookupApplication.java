package com.jasonharris.customerlookup;

import com.jasonharris.customerlookup.dao.CustomerDAO;
import com.jasonharris.customerlookup.resources.CustomerSearchResource;
import io.dropwizard.Application;
import io.dropwizard.jdbi3.JdbiFactory;
import io.dropwizard.setup.Environment;
import org.jdbi.v3.core.Jdbi;

public class CustomerLookupApplication extends Application<CustomerLookupConfig> {
    @Override
    public void run(CustomerLookupConfig configuration, Environment environment) {
        final JdbiFactory factory = new JdbiFactory();
        final Jdbi jdbi = factory.build(environment, configuration.getDataSourceFactory(), configuration.getDatasourceName());
        CustomerDAO customerDAO = jdbi.onDemand(CustomerDAO.class);

        CustomerSearchResource customerLookupResource = new CustomerSearchResource(customerDAO);
        environment.jersey().register(customerLookupResource);
    }

    public static void main(String[] args) throws Exception {
        new CustomerLookupApplication().run(args);
    }

}
