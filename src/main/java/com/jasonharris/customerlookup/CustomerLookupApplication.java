package com.jasonharris.customerlookup;

import com.jasonharris.customerlookup.dao.CustomerDAO;
import com.jasonharris.customerlookup.resources.CustomerSearchResource;
import io.dropwizard.Application;
import io.dropwizard.jdbi3.JdbiFactory;
import io.dropwizard.setup.Environment;
import org.jdbi.v3.core.Jdbi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustomerLookupApplication extends Application<CustomerLookupConfig> {
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerLookupApplication.class);

    @Override
    public void run(CustomerLookupConfig configuration, Environment environment) {
        final JdbiFactory factory = new JdbiFactory();
        final Jdbi jdbi = factory.build(environment, configuration.getDataSourceFactory(), configuration.getDatasourceName());

        CustomerDAO customerDAO = initialiseDB(jdbi);

        if (configuration.isAddTestCustomers()) {
            addTestCustomers(customerDAO);
        }

        CustomerSearchResource customerLookupResource = new CustomerSearchResource(customerDAO);
        environment.jersey().register(customerLookupResource);
    }

    private void addTestCustomers(CustomerDAO customerDAO) {
        LOGGER.info("Adding some Test Customers");
        customerDAO.insert("Jones","Kay","0305 1200287");
        customerDAO.insert("Jones","Phil","0985 2200487");
        customerDAO.insert("Patel","Pratik","0585 3200487");
    }

    public static CustomerDAO initialiseDB(Jdbi jdbi) {
        CustomerDAO customerDAO = jdbi.onDemand(CustomerDAO.class);
        customerDAO.createTable();
        return customerDAO;
    }

    public static void main(String[] args) throws Exception {
        new CustomerLookupApplication().run(args);
    }

}
