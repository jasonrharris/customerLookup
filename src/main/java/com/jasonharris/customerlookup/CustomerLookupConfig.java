package com.jasonharris.customerlookup;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@SuppressWarnings("unused") //values set by Dropwizard from config file
public class CustomerLookupConfig extends Configuration {

    /**
     * A factory that creates a DB source.
     */
    @NotNull
    @Valid
    private DataSourceFactory dataSourceFactory
            = new DataSourceFactory();

    @NotNull
    @Valid
    private String datasourceName;

    @NotNull
    @Valid
    private boolean addTestCustomers;

     /**
     * @return An instance of database factory deserialized from the
     * configuration file passed as a command-line argument to the application.
     */
    @JsonProperty("database")
    DataSourceFactory getDataSourceFactory() {
        return dataSourceFactory;
    }

    @JsonProperty("dataSourceName")
    String getDatasourceName() {
        return datasourceName;
    }

    public boolean isAddTestCustomers() {
        return addTestCustomers;
    }
}
