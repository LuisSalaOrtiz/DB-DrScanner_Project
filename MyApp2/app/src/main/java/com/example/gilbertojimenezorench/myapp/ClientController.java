package com.example.gilbertojimenezorench.myapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class ClientController {

    URL url;
    String request;
    User user;
    Context context;
    private Patients patient;
    private String qrcode="", pfname="", plname="", ssn="", email="", mstatus="", gender="", phone="", weight="", height="", blood="", address="", hcname="", hcnum="", dname="", specialty="", cname="", severity="";
    private PersonalInfo info;
    private ArrayList<Visits> vlist;
    private int pid=1, vid=1;
    private String hcexp="", vdate="", birth="";
    private Healthcare health;
    private Doctor doctor;
    private Visits visit;
    private Conditions conditions;
    private Diagnostics diagnostic;
    private boolean postState=false;

    private static ClientController instance = null;

    protected ClientController() {
        user = new User("","","");
        doctor = new Doctor(dname,specialty);
        conditions = new Conditions(cname, severity);
        diagnostic = new Diagnostics();
        visit = new Visits(vid, vdate, doctor, diagnostic);
        vlist=new ArrayList<>();
        health = new Healthcare(hcname,hcnum, hcexp);
        info = new PersonalInfo(birth, email, mstatus, gender, phone, weight, height, blood,  new Address(address), health);
        patient = new Patients(qrcode,pid,pfname,plname,ssn,info, vlist);
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
    public Patients callGetData(String givenUrl, String givenRequest, Context context)
    {
        this.context = context;

        String link = "https://morning-caverns-51343.herokuapp.com/" + givenUrl;
        try {
            url = new URL(link);
        } catch (MalformedURLException e) {
            //           Toast.makeText(context, "Connection Failed. Please Try again later.", Toast.LENGTH_LONG).show();
            new AlertDialog.Builder(context)
                    .setTitle("Connection Error!")
                    .setMessage("Connection Failed. Please Try again later.")
                    .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // continue with delete
                            return;
                        }
                    })
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
            e.printStackTrace();
        }
        request = givenRequest;
        new getdata().execute();

        //https://morning-caverns-51343.herokuapp.com/
        return patient;
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
            //Toast.makeText(context, "Connection Failed. Please Try again later.", Toast.LENGTH_LONG).show();
            new AlertDialog.Builder(context)
                    .setTitle("Connection Error!")
                    .setMessage("Connection Failed. Please Try again later.")
                    .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // continue with delete
                            return;
                        }
                    })
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
            e.printStackTrace();
        }
        request = givenRequest;
        new getdata().execute();

        //https://morning-caverns-51343.herokuapp.com/
        return user;
    }

    /**
     * @param givenUrl
     * @param jsonObject
     * @param context
     * @return
     */
    public boolean callPostData(String givenUrl, JSONObject jsonObject, Context context)
    {
        this.context = context;
        String link = "https://morning-caverns-51343.herokuapp.com/" + givenUrl;
        try {
            url = new URL(link);
        } catch (MalformedURLException e) {
            //Toast.makeText(context, "Connection Failed. Please Try again later.", Toast.LENGTH_LONG).show();
            new AlertDialog.Builder(context)
                    .setTitle("Connection Error!")
                    .setMessage("Connection Failed. Please Try again later.")
                    .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // continue with delete
                            return;
                        }
                    })
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
            e.printStackTrace();
        }

        new postdata(jsonObject).execute();

        return postState;
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

            String line = "";
            String res = "";
            JSONObject jsonObj;
            JSONArray jsonArray;
            JSONObject originalObj;

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

                    while ((line = bufferedReader.readLine()) != null) {
                        res += line;
                    }


                    /* Close Stream */
                    if (null != inputStream) {
                        inputStream.close();
                    }

                    if(request.equals("users"))
                    {
                        res = res.replace("[", "");
                        res = res.replace("]", "");
                        jsonObj= new JSONObject(res);
                        user.setEmail(jsonObj.getString("email"));
                        user.setPassword(jsonObj.getString("password"));
                        user.setType(jsonObj.getString("type"));
                    }
                    else {
                        if (request.equals("patients")) {
                            jsonArray = new JSONArray(res);
                            originalObj = new JSONObject(jsonArray.get(0).toString());


                            //Patient regular parameters
                            qrcode = originalObj.getString("qrcode");
                            pid = originalObj.getInt("pid");
                            pfname = originalObj.getString("pfirst");
                            plname = originalObj.getString("plast");
                            ssn = originalObj.getString("ssn");

                            //Healthcare Parameters
                            hcname = originalObj.getString("hcname");
                            hcnum = originalObj.getString("hcnum");

                            hcexp = originalObj.getString("hcexp").replace("T00:00:00.000Z","");
                            health.setHcname(hcname);
                            health.setHcnum(hcnum);
                            health.setHcexp(hcexp);

                            //PersonalInfo parameters
                            birth = originalObj.getString("birth").replace("T00:00:00.000Z","");
                            email = originalObj.getString("email");
                            mstatus = originalObj.getString("marital");
                            gender = originalObj.getString("gender");
                            phone = originalObj.getString("phone");
                            weight = originalObj.getString("weight");
                            height = originalObj.getString("height");
                            blood = originalObj.getString("blood");
                            address = originalObj.getString("address");
                            info.setAge(birth);
                            info.setEmail(email);
                            info.setMstatus(mstatus);
                            info.setGender(gender);
                            info.setPhone(phone);
                            info.setWeight(weight);
                            info.setHeight(height);
                            info.setBlood(blood);
                            info.setAddressInfo(new Address(address));
                            info.setHealth(health);

                            //Doctor Parameters
                            dname = originalObj.getString("dfirst") + " " + originalObj.getString("dlast");
                            specialty = originalObj.getString("specialty");
                            doctor.setDname(dname);
                            doctor.setSpecialty(specialty);

                            boolean visitExists = true;
                            JSONObject obj;
                            for (int i = 0; i < jsonArray.length(); i++) {
                                // & patient.getVisits().contains(jsonArray.getJSONObject(i).getInt("vid"))

                                obj = new JSONObject(jsonArray.get(i).toString());
                                if (vlist.isEmpty()) {
                                    System.out.println(obj);
                                    cname = obj.getString("cname");
                                    severity = obj.getString("severity");
                                    conditions.setCondName(cname);
                                    conditions.setSeverity(severity);
                                    diagnostic.add(conditions);
                                    vdate = originalObj.getString("vdate").replace("T00:00:00.000Z","");
                                    vid = obj.getInt("vid");
                                    visit = new Visits(vid, vdate, doctor, diagnostic);
                                    vlist.add(visit);
                                } else {
                                    Diagnostics visitDiagnostic;
                                    ArrayList<Visits> list = new ArrayList<>();
                                    for (Visits visit : vlist) {
                                        if (visit.getVid() == obj.getInt("vid")) {
                                            visitDiagnostic = visit.getDiagnostic();
                                            cname = obj.getString("cname");
                                            severity = obj.getString("severity");
                                            conditions = new Conditions(cname, severity);
                                            visitDiagnostic.add(conditions);
                                            visit.setDiagnostic(visitDiagnostic);
                                            visitExists = true;
                                        } else {
                                            visitExists = false;
                                        }
                                        list.add(visit);
                                    }
                                    vlist = list;
                                    if (!visitExists) {
                                        cname = obj.getString("cname");
                                        severity = obj.getString("severity");
                                        conditions = new Conditions(cname, severity);
                                        Diagnostics newDiag = new Diagnostics();
                                        newDiag.add(conditions);
                                        vdate = originalObj.getString("vdate").replace("T00:00:00.000Z","");
                                        vid = obj.getInt("vid");
                                        visit = new Visits(vid, vdate, doctor, newDiag);
                                        vlist.add(visit);
                                    }
                                }
                            }
                            //Setting patient information
                            patient.setQrcode(qrcode);
                            patient.setPid(pid);
                            patient.setPfname(pfname);
                            patient.setPlname(plname);
                            patient.setSsn(ssn);
                            patient.setInfo(info);
                            patient.setVisits(vlist);
                            return result;
                        }
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

        }

    }

    /**
     * Inner class used to retrieve Information
     */
    public class postdata extends AsyncTask<Void, Integer, String> {

        JSONObject postjsonObject;

        public postdata(JSONObject postingJObj)
        {
            postjsonObject = postingJObj;
        }


        @Override
        protected String doInBackground(Void... params) {
            String result = "";
            System.out.println("Do background");
            HttpURLConnection connection = null;
            DataOutputStream printout;
            DataInputStream input;
            StringBuilder sb = new StringBuilder();
            int statusCode = 0;
//
//            try {
//
//                connection = (HttpURLConnection) url.openConnection();
//
//                connection.setRequestProperty("Content-Type", "application/json");
//                connection.setRequestProperty("Accept", "application/json");
//                connection.setRequestMethod("POST");
//                connection.setDoInput(true);
//                connection.setDoOutput(true);
//                // is output buffer writter
//                //set headers and method
//                Writer writer = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream(), "UTF-8"));
//                writer.write(postjsonObject.toString());
//                // json data
//                writer.close();
//
//                statusCode = connection.getResponseCode();
//                System.out.println("Async call code: " + connection.getResponseCode());
//
//                if (statusCode == 200) {
//                    System.out.println("Server responded with code: " + statusCode);
//
//                    postState = true;
//
//                } else {
//                    System.out.println("error");
//                    new AlertDialog.Builder(context)
//                            .setMessage("Internet connection is not available. Try again later.")
//                            .setIcon(android.R.drawable.ic_dialog_alert)
//                            .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
//
//                                public void onClick(DialogInterface dialog, int whichButton) {
//
//                                }})
//                            .setNegativeButton(android.R.string.no, null).show();
//                    postState=false;
//                }
//
//            } catch (Exception e) {
//                System.out.println(e);
//            } finally {
//                connection.disconnect();
//                System.out.println("disconnected");
//            }


            try {

                connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput (true);
                connection.setDoOutput (true);
                connection.setUseCaches (false);
                connection.setRequestProperty("Content-Type","application/json");
                connection.setRequestMethod("POST");
                connection.setConnectTimeout(10000);
                connection.setReadTimeout(10000);
                connection.setRequestProperty("Host", "https://morning-caverns-51343.herokuapp.com/");
                connection.connect();

                //Post JSON JSONObject here
//                OutputStreamWriter out = new   OutputStreamWriter(connection.getOutputStream());
//                out.write(postjsonObject.toString());
//                out.close();

                printout = new DataOutputStream(connection.getOutputStream ());
                printout.writeBytes(URLEncoder.encode(postjsonObject.toString(),"UTF-8"));
                printout.flush ();
                printout.close ();

                int HttpResult =connection.getResponseCode();
                if(HttpResult ==HttpURLConnection.HTTP_OK){
                    BufferedReader br = new BufferedReader(new InputStreamReader(
                            connection.getInputStream(),"utf-8"));
                    String line = null;
                    while ((line = br.readLine()) != null) {
                        sb.append(line + "\n");
                    }
                    br.close();

                    System.out.println(""+sb.toString());

                }else{
                    System.out.println(connection.getResponseMessage());
                }
            } catch (MalformedURLException e) {

                e.printStackTrace();
            }
            catch (IOException e) {

                e.printStackTrace();
            } finally{
                if(connection!=null)
                    connection.disconnect();
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