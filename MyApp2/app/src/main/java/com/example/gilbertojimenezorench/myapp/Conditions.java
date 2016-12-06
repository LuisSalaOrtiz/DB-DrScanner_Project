package com.example.gilbertojimenezorench.myapp;

/**
 * Created by alber on 11/20/2016.
 */

public class Conditions {

    private String condName, severity;


    /**
     * @param cName
     * @param severity
     */
    public Conditions(String cName, String severity)
    {
        condName = cName;
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


}
