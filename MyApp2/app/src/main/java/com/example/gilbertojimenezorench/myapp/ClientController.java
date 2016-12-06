package com.example.gilbertojimenezorench.myapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;

public class ClientController {

    URL url;
    String request;
    User user;
    Context context;

    private static ClientController instance = null;

    protected ClientController() {
        user = new User("","","");
    }

    /**
     * Controller Class Singleton
     * @return class instance
     */
    public static ClientController getInstance()
    {
        if(instance == null) {
            instance = new ClientController();
        }
        return instance;
    }

    /**
     * This method start the query for getting info from the db
     * @param givenUrl
     */
    public void callGetData(String givenUrl, String givenRequest, Context context)
    {
        this.context = context;

        String link = "https://morning-caverns-51343.herokuapp.com/" + givenUrl;
        try {
            url = new URL(link);
        } catch (MalformedURLException e) {
            Toast.makeText(context, "Connection Failed. Please Try again later.", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
        request = givenRequest;
        new getdata().execute();

        //https://morning-caverns-51343.herokuapp.com/
    }

    /**
     * This method start the query for getting info from the db
     * @param givenUrl
     */
    public User callGetUserData(String givenUrl, String givenRequest, Context context)
    {
        this.context = context;
        String link = "https://morning-caverns-51343.herokuapp.com/" + givenUrl;
        try {
            url = new URL(link);
        } catch (MalformedURLException e) {
            Toast.makeText(context, "Connection Failed. Please Try again later.", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
        request = givenRequest;
        new getdata().execute();

        //https://morning-caverns-51343.herokuapp.com/
        return user;
    }

    /**
     * Inner class used to retrieve Information
     */
    public class getdata extends AsyncTask<Void, Integer, String> {


        @Override
        protected String doInBackground(Void... params) {
            String result = "";
            System.out.println("Do background");
            HttpURLConnection connection = null;
            int statusCode = 0;

            try {

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

                    res = res.replace("[", "");
                    res = res.replace("]", "");
                    JSONObject jsonObj = new JSONObject(res);

            /* Close Stream */
                    if (null != inputStream) {
                        inputStream.close();
                    }

                    System.out.println("Adding 5 seconds to app!");
//                    Timer timer = new Timer();
//                    timer.schedule(new TimerTask() {
//                        @Override
//                        public void run() {
//                            System.out.println("Adding 5 seconds to app!");
//                        }
//                    }, 5000);


                    if(request.equals("users"))
                    {
      //                  if(jsonObj.getString("email").equals(""))
                        user.setEmail(jsonObj.getString("email"));
                        user.setPassword(jsonObj.getString("password"));
                        user.setType(jsonObj.getString("type"));
//                        if(jsonObj.getString("email").equals(user.getEmail()))
//                        {
//                            if(jsonObj.getString("password").equals(user.getPassword()))
//                            {
//                                if(jsonObj.getString("type").equals("admin"))
//                                {
//                                    Intent intent = new Intent(ClientController.this, MainActivity.class);
//                                    intent.putExtra("user", "Welcome");
//                                    Toast.makeText(ClientController.this, "You are logged in.", Toast.LENGTH_LONG).show();
//                                    startActivityForResult(intent, 1);
//                                }
//                                else
//                                {
//                                    Intent intent = new Intent(ClientController.this, GeneralUserActivity.class);
//                                    intent.putExtra("user", "Welcome");
//                                    Toast.makeText(ClientController.this, "You are logged in.", Toast.LENGTH_LONG).show();
//                                    startActivityForResult(intent, 1);
//                                }
//                            }
//                            else
//                            {
//                                Toast.makeText(ClientController.this, "Password is incorrect. Please Try again.", Toast.LENGTH_SHORT).show();
//                            }
//                        }
//                        else
//                        {
//                            Toast.makeText(ClientController.this, "User doesn't exist. Please Register.", Toast.LENGTH_SHORT).show();
//                            Intent intent = new Intent(ClientController.this, RegisterActivity.class);
//                            startActivityForResult(intent, 2);
//                        }
                    }

                } else {
                    System.out.println("error");
                    new AlertDialog.Builder(context)
                            .setMessage("Internet connection is not available. Try again later.")
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {

                                public void onClick(DialogInterface dialog, int whichButton) {
                                    //Toast.makeText(ClientController.this, "Yaay", Toast.LENGTH_SHORT).show();
                                }})
                            .setNegativeButton(android.R.string.no, null).show();
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