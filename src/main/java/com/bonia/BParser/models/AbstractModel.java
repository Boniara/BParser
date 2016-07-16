package com.bonia.BParser.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class AbstractModel {

    @JsonProperty("id")
    private long id;

    public AbstractModel() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
