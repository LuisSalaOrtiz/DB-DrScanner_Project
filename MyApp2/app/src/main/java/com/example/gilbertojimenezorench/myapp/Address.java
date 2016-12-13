package com.example.gilbertojimenezorench.myapp;

import java.io.Serializable;

/**
 * Created by alber on 11/20/2016.
 */

public class Address implements Serializable{

    private String address;
    public Address(String address)
    {
        this.address= address;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
