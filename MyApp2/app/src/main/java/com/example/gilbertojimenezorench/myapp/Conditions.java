package com.example.gilbertojimenezorench.myapp;

import java.io.Serializable;

/**
 * Created by alber on 11/20/2016.
 */

public class Conditions implements Serializable{

    private String condName, severity;

    /**
     * @param cName
     * @param severity
     */
    public Conditions(String cName, String severity)
    {
        this.condName = cName;
        this.severity = severity;

    }

    /**
     * @return
     */
    public String getCondName() {
        return condName;
    }

    /**
     * @param condName
     */
    public void setCondName(String condName) {
        this.condName = condName;
    }

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }
}
