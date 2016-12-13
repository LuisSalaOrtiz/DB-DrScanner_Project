package com.example.gilbertojimenezorench.myapp;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by alber on 11/20/2016.
 */

public class Visits implements Serializable {

    private int vid;
    private String vdate;
    private Doctor doctor;
    private Diagnostics diagnostic;


    public Visits(int vid, String vdate, Doctor doctor, Diagnostics diagnostic) {
        this.vid=vid;
        this.vdate = vdate;
        this.doctor = doctor;
        this.diagnostic = diagnostic;
    }

    public int getVid() {
        return vid;
    }

    public void setVid(int vid) {
        this.vid = vid;
    }

    public String getVdate() {
        return vdate;
    }

    public void setVdate(String vdate) {
        this.vdate = vdate;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Diagnostics getDiagnostic() {
        return diagnostic;
    }

    public void setDiagnostic(Diagnostics diagnostic) {
        this.diagnostic = diagnostic;
    }
}
