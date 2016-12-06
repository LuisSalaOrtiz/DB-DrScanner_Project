package com.example.gilbertojimenezorench.myapp;

import java.util.Date;

/**
 * Created by alber on 11/20/2016.
 */

public class Visits {

    private Date vdate;
    private Doctor doctor;

    public Visits(Date vdate, Doctor doctor) {
        this.vdate = vdate;
        this.doctor = doctor;
    }

    public Date getVdate() {
        return vdate;
    }

    public void setVdate(Date vdate) {
        this.vdate = vdate;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }
}
