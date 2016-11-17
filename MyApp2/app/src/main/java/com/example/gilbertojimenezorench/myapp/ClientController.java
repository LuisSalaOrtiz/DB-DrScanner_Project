package com.example.gilbertojimenezorench.myapp;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ClientController extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new getdata().execute();

    }


    /**
     * Method to retrieve Information
     */
    public class getdata extends AsyncTask<Void, Integer, String> {


        @Override
        protected String doInBackground(Void... params) {
            String result = "";
            System.out.println("Do background");
            HttpURLConnection connection = null;
            int statusCode = 0;

            try {
                URL url = new URL("http://morning-caverns-51343.herokuapp.com/db");

                connection = (HttpURLConnection) url.openConnection();

                connection.setRequestProperty("Content-Type", "application/json");
                connection.setRequestMethod("GET");

                statusCode = connection.getResponseCode();
                System.out.println("Async call code: " + connection.getResponseCode());

                if (statusCode == 200) {
                    System.out.println("Server responded with code: " + statusCode);

                    InputStream inputStream = new BufferedInputStream(connection.getInputStream());

                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

                    String line = "";
                    String res = "";

                    while ((line = bufferedReader.readLine()) != null) {
                        res += line;
                    }

            /* Close Stream */
                    if (null != inputStream) {
                        inputStream.close();
                    }

                    System.out.println("Hello: " + res);
                    // Code goes here

                } else {
                    System.out.println("error");
                }

            } catch (Exception e) {
                System.out.println(e);
            } finally {
                connection.disconnect();
                System.out.println("disconnected");
            }

            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);


            // progressDialog.setCancelable(true);
            //  }
        }

    }

}