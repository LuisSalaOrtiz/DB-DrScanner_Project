/*
package com.example.gilbertojimenezorench.myapp;
import android.support.v7.app.AppCompatActivity;

import java.util.Scanner;      // Required for the scanner
import java.io.File;               // Needed for File and IOException
import java.io.FileNotFoundException; //Required for exception throw
*/
/**
 * Created by HP on 8/31/2016.
 *//*

public class MarketFile extends AppCompatActivity{
    public void main(String[] args) throws FileNotFoundException // Throws Clause Added
        {
            // Getting String From Scanner Ibsn

            String searchString = this.getIntent().getStringExtra("CONTENTS");

            // open the data file
            File file = new File("ListaSuper.txt");

            // create a scanner from the file
            Scanner inputFile = new Scanner (file);

            // set up the scanner to use "," as the delimiter
            inputFile.useDelimiter("[\\r,]");

            // While there is another line to read.
            while(inputFile.hasNext())
            {
                // read the 3 parts of the line
                String country = inputFile.next(); //Read country
                String capital = inputFile.next(); //Read capital
                String population = inputFile.next(); //Read Population

                //Check if user input is a match and if true print out info.
                if(searchString.equals(country))
                {
                    System.out.println("Yay!");
                }
                else
                {
                    System.out.println("Fail!");
                }
            }

            // be polite and close the file
            inputFile.close();
        }
    }

*/
