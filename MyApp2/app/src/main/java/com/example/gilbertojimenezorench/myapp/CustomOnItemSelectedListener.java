package com.example.gilbertojimenezorench.myapp;

import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

/**
 * Created by HP on 10/13/2016.
 */

public class CustomOnItemSelectedListener implements AdapterView.OnItemSelectedListener {
private String selection = "5";
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id)
    {selection = parent.getItemAtPosition(pos).toString();}

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {}

    public String getSpinnerSelection ()
    {
        return selection;
    }
}
