package com.vietinterview.getbee.model;

/**
 * Created by hiepnguyennghia on 11/5/18.
 * Copyright © 2018 Vietinterview. All rights reserved.
 */
public class Carrer {
    private int id;
    private String name;

    public Carrer(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
