package com.jasonharris.customerlookup;

import io.dropwizard.Application;
import io.dropwizard.jdbi3.JdbiFactory;
import io.dropwizard.setup.Environment;
import org.jdbi.v3.core.Jdbi;

public class CustomerLookupApplication extends Application<CustomerLookupConfig> {
    @Override
    public void run(CustomerLookupConfig configuration, Environment environment) {
        final JdbiFactory factory = new JdbiFactory();
        final Jdbi jdbi = factory.build(environment, configuration.getDataSourceFactory(), configuration.getDatasourceName());
    }

}
