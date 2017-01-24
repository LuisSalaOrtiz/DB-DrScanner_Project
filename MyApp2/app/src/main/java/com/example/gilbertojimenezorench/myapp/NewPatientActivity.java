package com.example.gilbertojimenezorench.myapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class NewPatientActivity extends AppCompatActivity {

    private Spinner spinnerMarital, spinnerBlood, spinnerMedicare;
    private Button submit;
    private EditText name,lastName,email,age,phoNumber,posAddress,socNumber,weight,height,cardNumber;
    private String Marital, Blood , MedCompany, gender, qrcode;
    ArrayList<String> selection= new ArrayList<>();
    public ProgressDialog progress = null;
    private JSONObject params;
    private JSONObject idsObject;
    public Intent myIntent;
    public ClientController controller;
    public Context context;
    public String pfirst, lastNametxt, emailtxt, agetxt, photxt, soctxt, postxt, weighttxt, heighttxt, cardNumbertxt, temp;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_patient);

        name = (EditText)findViewById(R.id.fnametxt);
        lastName = (EditText)findViewById(R.id.lnametxt);
        email = (EditText)findViewById(R.id.remailtxt);
        age = (EditText)findViewById(R.id.age);
        phoNumber= (EditText)findViewById(R.id.rcelltext);
        socNumber = (EditText)findViewById(R.id.socialNumber);
        posAddress = (EditText)findViewById(R.id.postalAddress);
        weight = (EditText)findViewById(R.id.weight);
        height = (EditText)findViewById(R.id.height);
        cardNumber = (EditText)findViewById(R.id.cardNumberMed);
        gender="";

        addListenerOnSpinnerItemSelection1();
        addListenerOnSpinnerItemSelection2();
        addListenerOnSpinnerItemSelection3();

        controller = ClientController.getInstance();
        context = NewPatientActivity.this;
        params = new JSONObject();
        idsObject = new JSONObject();

        progress = new ProgressDialog(context);
        progress.setMessage("Posting Patient...");
        progress.setMax(100);
        progress.setProgress(0);


        submit = (Button) findViewById(R.id.submitBtn);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                qrcode = getIntent().getStringExtra("qrcode");
                pfirst = name.getText().toString();
                lastNametxt = lastName.getText().toString();
                emailtxt = email.getText().toString();
                agetxt = age.getText().toString();
                photxt = phoNumber.getText().toString();
                soctxt = socNumber.getText().toString();
                postxt = posAddress.getText().toString();
                weighttxt = weight.getText().toString();
                heighttxt = height.getText().toString();
                cardNumbertxt = cardNumber.getText().toString();

                RadioGroup g = (RadioGroup) findViewById(R.id.radioGroup);

// Returns an integer which represents the selected radio button's ID
                int selectedID = g.getCheckedRadioButtonId();

// Gets a reference to our "selected" radio button
                RadioButton b = (RadioButton) findViewById(selectedID);

// Now you can get the text or whatever you want from the "selected" radio button
                gender = b.getText().toString();


                myIntent = new Intent(NewPatientActivity.this,DoctorPatientFileActivity.class);

                myIntent.putExtra("BLOOD",Blood);
                myIntent.putExtra("MEDCOM",MedCompany);
                myIntent.putExtra("MARITAL",Marital);
                myIntent.putExtra("NAME",pfirst);
                myIntent.putExtra("LASTNAME",lastNametxt);
                myIntent.putExtra("EMAIL",emailtxt);
                myIntent.putExtra("AGE", Integer.valueOf(agetxt));
                myIntent.putExtra("PHONE",photxt);
                myIntent.putExtra("SOCIAL",soctxt);
                myIntent.putExtra("POSTAL",postxt);
                myIntent.putExtra("WEIGHT",weighttxt);
                myIntent.putExtra("HEIGHT",heighttxt);
                myIntent.putExtra("CARDNUM",cardNumbertxt);
                myIntent.putExtra("RADIO",gender);
                myIntent.putExtra("qrcode", qrcode);
                myIntent.putStringArrayListExtra("SELECT",selection);


                progress.show();
                //Starts posting the patient step by step


                try
                {
                    params.put("qrcode", qrcode);
                    params.put("pfirst", pfirst);
                    params.put("plast", lastNametxt);
                    params.put("ssn", soctxt);
                    params.put("address", postxt);
                    params.put("hcname", MedCompany);
                    params.put("hcnum", cardNumbertxt);
                    params.put("email", emailtxt);
                    params.put("marital", Marital);
                    params.put("gender", gender);
                    params.put("phone", photxt);
                    params.put("weight", weighttxt);
                    params.put("height", heighttxt);
                    params.put("blood", Blood);
                    params.put("age", agetxt);
                    params.put("number", selection.size());
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                for(int i=0; i<selection.size();i++) {
                    temp = selection.get(i).replace("- ", "");
                    try {
                        params.put("cname"+(i+1), temp);
//                        params.put("severity"+(i+1), "High");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                controller.callPostData("post/patient/", params, context);

                (new Handler()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        progress.setProgress(100);
                        progress.dismiss();
                        startActivity(myIntent);
                    }
                }, 4000);
            }
        });
    }

    public void selectItems( View view) {
        boolean checked = ((CheckBox) view).isChecked();
        switch (view.getId())

        {
            case R.id.checkBox1:
                if (checked) {selection.add("- Diabetes");}
                break;

            case R.id.checkBox2:
                if (checked) {selection.add("- Heart Disease");}
                break;

            case R.id.checkBox3:
                if (checked) {selection.add("- Hepatitis");}
                break;

            case R.id.checkBox4:
                if (checked) {selection.add("- Leukemia");}
                break;

            case R.id.checkBox5:
                if (checked) {selection.add("- Hipertension");}
                break;

            case R.id.checkBox6:
                if (checked) {selection.add("- Liver Disease");}
                break;

            case R.id.checkBox7:
                if (checked) {selection.add("- Cancer");}
                break;

            case R.id.checkBox8:
                if (checked) {selection.add("- Ulcers");}
                break;

            case R.id.checkBox9:
                if (checked) {selection.add("- Autism");}
                break;

            case R.id.checkBox10:
                if (checked) {selection.add("- Kidney Problem");}
                break;

            case R.id.checkBox11:
                if (checked) {selection.add("- Lung Problems");}
                break;

            case R.id.checkBox12:
                if (checked) {selection.add("- Vision Disorder");}
                break;
        }


    }

    public void addListenerOnSpinnerItemSelection1() {
        spinnerMarital = (Spinner) findViewById(R.id.mStatus);
        spinnerMarital.setOnItemSelectedListener(new CustomOnItemSelectedListener());
        Marital = String.valueOf(spinnerMarital.getSelectedItem());
    }

    public void addListenerOnSpinnerItemSelection2() {
        spinnerBlood = (Spinner) findViewById(R.id.bStatus);
        spinnerBlood.setOnItemSelectedListener(new CustomOnItemSelectedListener());
        Blood = String.valueOf(spinnerBlood.getSelectedItem());
    }

    public void addListenerOnSpinnerItemSelection3() {
        spinnerMedicare = (Spinner) findViewById(R.id.medStatus);
        spinnerMedicare.setOnItemSelectedListener(new CustomOnItemSelectedListener());
        MedCompany = String.valueOf(spinnerMedicare.getSelectedItem());
    }
}
