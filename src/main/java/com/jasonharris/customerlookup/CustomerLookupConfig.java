package com.jasonharris.customerlookup;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.db.DataSourceFactory;
import org.hibernate.validator.constraints.NotEmpty;

import io.dropwizard.Configuration;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

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

     /**
     * @return An instance of database factory deserialized from the
     * configuration file passed as a command-line argument to the application.
     */
    @JsonProperty("database")
    public DataSourceFactory getDataSourceFactory() {
        return dataSourceFactory;
    }

    @JsonProperty("database")
    public void setDataSourceFactory(DataSourceFactory factory) {
        this.dataSourceFactory = factory;
    }

    @JsonProperty("dataSourceName")
    public String getDatasourceName() {
        return datasourceName;
    }

    @JsonProperty("dataSourceName")
    public void setDatasourceName(String datasourceName) {
        this.datasourceName = datasourceName;
    }
}
