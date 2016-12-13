package com.example.gilbertojimenezorench.myapp;

import java.io.Serializable;

/**
 * Created by alber on 11/20/2016.
 */

public class Doctor implements Serializable {

    String dname, specialty;

    public Doctor( String dname, String specialty){
        this.dname = dname;
        this.specialty = specialty;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }
}
