package com.example.gilbertojimenezorench.myapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.util.Pair;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

public class ClientController {

    URL url;
    String request;
    User user;
    Context context;
    private Patients patient;
    private String qrcode="", pfname="", plname="", ssn="", email="", mstatus="", gender="", phone="", weight="", height="", blood="", address="", hcname="", hcnum="", dname="", specialty="", cname="", severity="";
    private PersonalInfo info;
    private ArrayList<Visits> vlist;
    private int pid=1, vid=1, age=0;
    private String hcexp="", vdate="";
    private Healthcare health;
    private Doctor doctor;
    private Visits visit;
    private Conditions conditions;
    private Diagnostics diagnostic;
    private boolean postState=false;
    private JSONObject patientParams;

    private static ClientController instance = null;

    protected ClientController() {
        user = new User("","","");
        qrcode="";
        pfname="";
        plname="";
        ssn="";
        email="";
        mstatus="";
        gender="";
        phone="";
        weight="";
        height="";
        blood="";
        address="";
        hcname="";
        hcnum="";
        dname="";
        specialty="";
        cname="";
        severity="";
        doctor = new Doctor(dname,specialty);
        conditions = new Conditions(cname, severity);
        diagnostic = new Diagnostics();
        visit = new Visits(vid, vdate, doctor, diagnostic);
        vlist=new ArrayList<>();
        health = new Healthcare(hcname,hcnum, hcexp);
        info = new PersonalInfo(age, email, mstatus, gender, phone, weight, height, blood,  new Address(address), health);
        patient = new Patients(qrcode,pid,pfname,plname,ssn,info, vlist);
        patientParams = new JSONObject();
    }

    public void reset()
    {
        user = new User("","","");
        doctor = new Doctor(dname,specialty);
        conditions = new Conditions(cname, severity);
        diagnostic = new Diagnostics();
        visit = new Visits(vid, vdate, doctor, diagnostic);
        vlist=new ArrayList<>();
        health = new Healthcare(hcname,hcnum, hcexp);
        info = new PersonalInfo(age, email, mstatus, gender, phone, weight, height, blood,  new Address(address), health);
        patient = new Patients(qrcode,pid,pfname,plname,ssn,info, vlist);
        patientParams = new JSONObject();
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
    public JSONObject callGetPatientData(String givenUrl, String givenRequest, Context context)
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
        return patientParams;
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

        switch(givenUrl)
        {
            case "post/user/":
                new postuser(jsonObject).execute();
                break;
            case "post/patient/":
                new postpatient(jsonObject, givenUrl).execute();
                break;
//            case "post/patient/part1/":
//                new postpatient(jsonObject, givenUrl).execute();
//                break;
//            case "post/patient/part2/":
//                new postpatient(jsonObject, givenUrl).execute();
//                break;
//            case "post/patient/part3/":
//                new postpatient(jsonObject, givenUrl).execute();
//                break;
//            case "post/patient/part4/":
//                new postpatient(jsonObject, givenUrl).execute();
//                break;
            default:
                break;
        }

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
                            age = originalObj.getInt("age");
                            email = originalObj.getString("email");
                            mstatus = originalObj.getString("marital");
                            gender = originalObj.getString("gender");
                            phone = originalObj.getString("phone");
                            weight = originalObj.getString("weight");
                            height = originalObj.getString("height");
                            blood = originalObj.getString("blood");
                            address = originalObj.getString("address");
                            info.setAge(age);
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
                } else if(request.equals("3Ids"))
                {
                    res = res.replace("[", "");
                    res = res.replace("]", "");
                    patientParams = new JSONObject(res);
                } else if(request.equals("vid"))
                {
                    res = res.replace("[", "");
                    res = res.replace("]", "");
                    patientParams = new JSONObject(res);
                } else if(request.equals("diagid"))
                {
                    res = res.replace("[", "");
                    res = res.replace("]", "");
                    patientParams = new JSONObject(res);
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
     * Inner class used to post user
     */
    public class postuser extends AsyncTask<JSONObject, Integer, String> {

        JSONObject postjsonObject;

        public postuser(JSONObject postingJObj)
        {
            postjsonObject = postingJObj;
        }


        @Override
        protected String doInBackground(JSONObject... params) {
            String result = "";
            System.out.println("Do background");
            HttpURLConnection conn = null;
            DataOutputStream printout;
            DataInputStream input;
            int statusCode = 0;


            try {

                conn = (HttpsURLConnection) url.openConnection();
                conn.setReadTimeout(10000);
                conn.setConnectTimeout(15000);
                conn.setRequestMethod("POST");
                conn.setDoInput(true);
                conn.setDoOutput(true);

                List<Pair> parameters = new ArrayList<>();
                parameters.add(new Pair("email", postjsonObject.get("email")));
                parameters.add(new Pair("password", postjsonObject.get("password")));
                parameters.add(new Pair("type", postjsonObject.get("type")));

                OutputStream os = conn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                writer.write(getQuery(parameters));
                writer.flush();
                writer.close();
                os.close();

                conn.connect();

                statusCode = conn.getResponseCode();
                System.out.println("Async call code: " + conn.getResponseCode());

                if (statusCode == 200) {
                    System.out.println("Server responded with code: " + statusCode);

                    postState = true;

                } else {
                    System.out.println("error");
                    new AlertDialog.Builder(context)
                            .setMessage("Internet connection is not available. Try again later.")
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {

                                public void onClick(DialogInterface dialog, int whichButton) {

                                }})
                            .setNegativeButton(android.R.string.no, null).show();
                    postState=false;
                }

            } catch (Exception e) {
                System.out.println(e);
            } finally {
                conn.disconnect();
                System.out.println("disconnected");
            }

            return result;
        }

        private String getQuery(List<Pair> params) throws UnsupportedEncodingException
        {
            StringBuilder result = new StringBuilder();
            boolean first = true;

            for (Pair pair : params)
            {
                if (first)
                    first = false;
                else
                    result.append("&");

                result.append(URLEncoder.encode(pair.first.toString(), "UTF-8"));
                result.append("=");
                result.append(URLEncoder.encode(pair.second.toString(), "UTF-8"));
            }

            return result.toString();
        }


        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);


            // progressDialog.setCancelable(true);
            //  }
        }

    }

    /**
     * Inner class used to post patient
     */
    public class postpatient extends AsyncTask<JSONObject, Integer, String> {

        JSONObject postjsonObject;
        String request;

        public postpatient(JSONObject postingJObj, String request)
        {
            postjsonObject = postingJObj;
            this.request=request;

        }


        @Override
        protected String doInBackground(JSONObject... params) {
            String result = "";
            System.out.println("Do background");
            HttpURLConnection conn = null;
            DataOutputStream printout;
            DataInputStream input;
            int statusCode = 0;


            try {

                conn = (HttpsURLConnection) url.openConnection();
                conn.setReadTimeout(10000);
                conn.setConnectTimeout(15000);
                conn.setRequestMethod("POST");
                conn.setDoInput(true);
                conn.setDoOutput(true);

                List<Pair> parameters = new ArrayList<>();

                switch(request)
                {
                    // 3 params
                    case "post/user/":
                        parameters.add(new Pair("email", postjsonObject.get("email")));
                        parameters.add(new Pair("password", postjsonObject.get("password")));
                        parameters.add(new Pair("type", postjsonObject.get("type")));
                        break;
                    // 17 or more parameters
                    case "post/patient/":
                        parameters.add(new Pair("qrcode", postjsonObject.get("qrcode")));
                        parameters.add(new Pair("pfirst", postjsonObject.get("pfirst")));
                        parameters.add(new Pair("plast", postjsonObject.get("plast")));
                        parameters.add(new Pair("ssn", postjsonObject.get("ssn")));
                        parameters.add(new Pair("address", postjsonObject.get("address")));
                        parameters.add(new Pair("hcname", postjsonObject.get("hcname")));
                        parameters.add(new Pair("hcnum", postjsonObject.get("hcnum")));
                        parameters.add(new Pair("email", postjsonObject.get("email")));
                        parameters.add(new Pair("marital", postjsonObject.get("marital")));
                        parameters.add(new Pair("gender", postjsonObject.get("gender")));
                        parameters.add(new Pair("phone", postjsonObject.get("phone")));
                        parameters.add(new Pair("weight", postjsonObject.get("weight")));
                        parameters.add(new Pair("height", postjsonObject.get("height")));
                        parameters.add(new Pair("blood", postjsonObject.get("blood")));
                        parameters.add(new Pair("age", postjsonObject.get("age")));
                        parameters.add(new Pair("number", postjsonObject.get("number")));

                        for(int i=0; i < ((int) postjsonObject.get("number")) ;i++) {
                            String condition="cname"+(i+1);
                            System.out.println("-----------------------------------------------------------------------------");
                            System.out.println(condition);
                            System.out.println(condition);
                            System.out.println(condition);
                            System.out.println("-----------------------------------------------------------------------------");
                            parameters.add(new Pair(condition, postjsonObject.get(condition)));
                        }
                        break;
//                    // 5 params
//                    case "post/patient/part1/":
//                        parameters.add(new Pair("qrcode", postjsonObject.get("qrcode")));
//                        parameters.add(new Pair("pfirst", postjsonObject.get("pfirst")));
//                        parameters.add(new Pair("plast", postjsonObject.get("plast")));
//                        parameters.add(new Pair("ssn", postjsonObject.get("ssn")));
//                        parameters.add(new Pair("address", postjsonObject.get("address")));
//                        parameters.add(new Pair("hcname", postjsonObject.get("hcname")));
//                        parameters.add(new Pair("hcnum", postjsonObject.get("hcnum")));
//                        break;
//                    // 2 params
//                    case "post/patient/part1.5/":
//                        parameters.add(new Pair("hcname", postjsonObject.get("hcname")));
//                        parameters.add(new Pair("hcnum", postjsonObject.get("hcnum")));
//                        break;
//                    // 2 params
//                    case "post/patient/part2/":
//                        parameters.add(new Pair("pid", postjsonObject.get("pid")));
//                        parameters.add(new Pair("vdate", postjsonObject.get("vdate")));
//                        break;
//                    // 12 params
//                    case "post/patient/part3/":
//                        parameters.add(new Pair("email", postjsonObject.get("email")));
//                        parameters.add(new Pair("marital", postjsonObject.get("marital")));
//                        parameters.add(new Pair("gender", postjsonObject.get("gender")));
//                        parameters.add(new Pair("phone", postjsonObject.get("phone")));
//                        parameters.add(new Pair("weight", postjsonObject.get("weight")));
//                        parameters.add(new Pair("height", postjsonObject.get("height")));
//                        parameters.add(new Pair("blood", postjsonObject.get("blood")));
//                        parameters.add(new Pair("pid", postjsonObject.get("pid")));
//                        parameters.add(new Pair("aid", postjsonObject.get("aid")));
//                        parameters.add(new Pair("hcid", postjsonObject.get("hcid")));
//                        parameters.add(new Pair("age", postjsonObject.get("age")));
//                        parameters.add(new Pair("vid", postjsonObject.get("vid")));
//                        break;
//                    // 3 params
//                    case "post/patient/part4/":
//                        parameters.add(new Pair("diagid", postjsonObject.get("diagid")));
//                        parameters.add(new Pair("cname", postjsonObject.get("cname")));
//                        parameters.add(new Pair("severity", postjsonObject.get("severity")));
//                        break;
                    default:
                        break;

                }

                OutputStream os = conn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                writer.write(getQuery(parameters));
                writer.flush();
                writer.close();
                os.close();

                conn.connect();

                statusCode = conn.getResponseCode();
                System.out.println("Async call code: " + conn.getResponseCode());

                if (statusCode == 200) {
                    System.out.println("Server responded with code: " + statusCode);

                    postState = true;

                } else {
                    System.out.println("error");
                    new AlertDialog.Builder(context)
                            .setMessage("Internet connection is not available. Try again later.")
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {

                                public void onClick(DialogInterface dialog, int whichButton) {

                                }})
                            .setNegativeButton(android.R.string.no, null).show();
                    postState=false;
                }

            } catch (Exception e) {
                System.out.println(e);
            } finally {
                conn.disconnect();
                System.out.println("disconnected");
            }

            return result;
        }

        private String getQuery(List<Pair> params) throws UnsupportedEncodingException
        {
            StringBuilder result = new StringBuilder();
            boolean first = true;

            for (Pair pair : params)
            {
                if (first)
                    first = false;
                else
                    result.append("&");

                result.append(URLEncoder.encode(pair.first.toString(), "UTF-8"));
                result.append("=");
                result.append(URLEncoder.encode(pair.second.toString(), "UTF-8"));
            }

            return result.toString();
        }


        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);


            // progressDialog.setCancelable(true);
            //  }
        }

    }

    /**
     * @param givenUrl
     * @param context
     * @return
     */
    public boolean callDeleteData(String givenUrl, Context context) {
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

        new deletepatient().execute();

        return postState;
    }

    /**
     * Inner class used to delete patient
     */
    public class deletepatient extends AsyncTask<Void, Integer, String> {


        @Override
        protected String doInBackground(Void... params) {
            String result = "";
            System.out.println("Do background");
            HttpURLConnection connection = null;
            int statusCode = 0;

            String line = "";
            String res = "";
            JSONObject jsonObj;

            try {

                connection = (HttpURLConnection) url.openConnection();

                connection.setRequestProperty("Content-Type", "application/json");
                connection.setRequestMethod("DELETE");

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


                }
            } catch (Exception e) {
                System.out.println(e);
                System.out.println("error");
                new AlertDialog.Builder(context)
                        .setMessage("Internet connection is not available. Try again later.")
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int whichButton) {
                                //Toast.makeText(ClientController.this, "Yaay", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton(android.R.string.no, null).show();
            } finally {
                connection.disconnect();
                System.out.println("disconnected");
            }

            return result;
        }

        @Override
        protected void onPostExecute (String result){
            super.onPostExecute(result);

        }

    }

}