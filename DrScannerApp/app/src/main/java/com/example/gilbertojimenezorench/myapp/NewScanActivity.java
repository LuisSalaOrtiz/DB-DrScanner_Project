package com.example.gilbertojimenezorench.myapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileNotFoundException;


public class NewScanActivity extends AppCompatActivity {

    String price, name, isbn, noData;
    TextView tag, value, scan, noApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_scan);

        scan = (TextView) findViewById(R.id.scan);
        tag = (TextView) findViewById(R.id.tag);
        value = (TextView) findViewById(R.id.value);
        noApp = (TextView) findViewById(R.id.noApp);


            String searchString = this.getIntent().getStringExtra("CONTENTS").trim();

            ArrayList<String> info = new ArrayList<String>();
            info.add("050313151357:Carmela:$6.99");
            info.add("028400055109:Lay'sStaxBBQ:$2.90");
            info.add("050000397600:Nestle Chocolate:$3.99");

            // While there is another line to read.
            for(String temp: info){

                String[] content = temp.split(":");
                isbn = content[0].trim();
                noData = "";


                //Checks if the scan number matches d file isbn
                if (searchString.equals(isbn)) {
                    name = content[1]; //Read name
                    price = content[2];; //Read price
                    break;
                } else {
                    noData = (" No Data :( ");
                }
            }


        Button addTo = (Button) findViewById(R.id.addTo);
        addTo.setOnClickListener(new View.OnClickListener() {
                                          @Override
                                          public void onClick(View v) {
                                            //  IntentIntegrator intent = new IntentIntegrator(MainActivity.this);
                                            //  intent.initiateScan();

                                          }
                                    });

        // To show the data on the activity
        if(noData.equals("")) {
            scan.setText(isbn);
            tag.setText(name);
            value.setText(price);
        }
        else {
            noApp.setText(noData);
        }


    }

}