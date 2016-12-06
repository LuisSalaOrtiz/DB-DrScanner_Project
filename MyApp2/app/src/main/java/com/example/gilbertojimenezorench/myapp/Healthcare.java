package com.example.gilbertojimenezorench.myapp;

import java.util.Date;

/**
 * Created by alber on 11/20/2016.
 */

public class Healthcare {

    private String hcname, hcnum;
    private Date hcexp;

    public Healthcare(String hcname, String hcnum, Date hcexp) {
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

    public Date getHcexp() {
        return hcexp;
    }

    public void setHcexp(Date hcexp) {
        this.hcexp = hcexp;
    }

    public String getHcnum() {
        return hcnum;
    }

    public void setHcnum(String hcnum) {
        this.hcnum = hcnum;
    }
}
