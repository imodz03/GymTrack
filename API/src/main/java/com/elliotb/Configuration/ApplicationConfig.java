package com.elliotb.Configuration;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class ApplicationConfig extends Configuration {

    @JsonProperty
    private String name;

    @JsonProperty
    private String password;

    @JsonProperty
    private String keyalias;

    @Valid
    @NotNull
    private DataSourceFactory database = new DataSourceFactory();

    @JsonProperty("database")
    public void setDataSourceFactory(DataSourceFactory factory) {
        this.database = factory;
    }

    @JsonProperty("database")
    public DataSourceFactory getDataSourceFactory() {
        return database;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getKeyalias() {
        return keyalias;
    }

    public void setKeyalias(String keyalias) {
        this.keyalias = keyalias;
    }
}
