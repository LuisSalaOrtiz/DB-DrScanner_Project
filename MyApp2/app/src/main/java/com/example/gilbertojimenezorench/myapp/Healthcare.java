package com.example.gilbertojimenezorench.myapp;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by alber on 11/20/2016.
 */

public class Healthcare implements Serializable {

    private String hcname, hcnum;
    private String hcexp;

    public Healthcare(String hcname, String hcnum, String hcexp) {
        this.hcname = hcname;
        this.hcnum = hcnum;
        this.hcexp = hcexp;
    }

    public String getHcname() {
        return hcname;
    }

    public void setHcname(String hcname) {
        this.hcname = hcname;
    }

    public String getHcexp() {
        return hcexp;
    }

    public void setHcexp(String hcexp) {
        this.hcexp = hcexp;
    }

    public String getHcnum() {
        return hcnum;
    }

    public void setHcnum(String hcnum) {
        this.hcnum = hcnum;
    }
}
