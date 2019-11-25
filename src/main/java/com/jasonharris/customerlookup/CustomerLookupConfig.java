package com.jasonharris.customerlookup;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotEmpty;

import io.dropwizard.Configuration;

public class CustomerLookupConfig extends Configuration {

    @NotEmpty
    private String jdbcUrl;

    @JsonProperty
    public String getJdbcUrl() {
        return jdbcUrl;
    }

    @JsonProperty
    public void setJdbcUrl(String jdbcUrl) {
        this.jdbcUrl = jdbcUrl;
    }
}
