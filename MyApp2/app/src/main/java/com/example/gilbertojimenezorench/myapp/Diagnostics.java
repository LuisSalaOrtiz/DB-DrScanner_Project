package com.example.gilbertojimenezorench.myapp;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by alber on 11/20/2016.
 *
 */

public class Diagnostics extends ArrayList<Conditions> implements Serializable {


    public ArrayList<Conditions> diagnostic;

    /**
     * This class will be a list of conditions. This is the main constructor.
     */
    public Diagnostics()
    {
        diagnostic = new ArrayList<>();
    }

    public void addCondition(String cname, String severity)
    {
        diagnostic.add(new Conditions(cname, severity));
    }


}
