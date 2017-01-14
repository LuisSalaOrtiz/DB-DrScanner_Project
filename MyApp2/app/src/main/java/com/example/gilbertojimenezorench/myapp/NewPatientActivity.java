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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;

public class NewPatientActivity extends AppCompatActivity {

    private Spinner spinnerMarital, spinnerBlood, spinnerMedicare;
    private Button submit;
    private EditText name,lastName,email,age,phoNumber,phyAddress,posAddress,socNumber,weight,height,cardNumber;
    private String Marital, Blood , MedCompany, gender, qrcode;
    private RadioButton radio1, radio2;
    ArrayList<String> selection= new ArrayList<String>();
    public ProgressDialog progress = null;
    private JSONObject params;
    private JSONObject idsObject;
    public Intent myIntent;
    public ClientController controller;
    public Context context;
    public int pid,aid,hcid,vid,diagid;



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
        radio1 = (RadioButton) findViewById(R.id.maleRadioBtn);
        radio2 = (RadioButton) findViewById(R.id.femaleRadioBtn);

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
                String nametxt = name.getText().toString();
                String lastNametxt = lastName.getText().toString();
                String emailtxt = email.getText().toString();
                String agetxt = age.getText().toString();
                String photxt = phoNumber.getText().toString();
                String soctxt = socNumber.getText().toString();
                String phytxt = phyAddress.getText().toString();
                String postxt = posAddress.getText().toString();
                String weighttxt = weight.getText().toString();
                String heighttxt = height.getText().toString();
                String cardNumbertxt = cardNumber.getText().toString();

                if(radio1.isSelected())
                {
                    gender="male";
                }
                else if(radio2.isSelected())
                {
                    gender="female";
                }


                myIntent = new Intent(NewPatientActivity.this,DoctorPatientFileActivity.class);


                myIntent.putExtra("BLOOD",Blood);
                myIntent.putExtra("MEDCOM",MedCompany);
                myIntent.putExtra("MARITAL",Marital);
                myIntent.putExtra("NAME",nametxt);
                myIntent.putExtra("LASTNAME",lastNametxt);
                myIntent.putExtra("EMAIL",emailtxt);
                myIntent.putExtra("AGE",agetxt);
                myIntent.putExtra("PHONE",photxt);
                myIntent.putExtra("SOCIAL",soctxt);
                myIntent.putExtra("PHYSICAL",phytxt);
                myIntent.putExtra("POSTAL",postxt);
                myIntent.putExtra("WEIGHT",weighttxt);
                myIntent.putExtra("HEIGHT",heighttxt);
                myIntent.putExtra("CARDNUM",cardNumbertxt);
                myIntent.putStringArrayListExtra("SELECT",selection);


                progress.show();
                //Starts posting the patient step by step

                //Step 1: Post part 1
                try
                {
                    params.put("qrcode", qrcode);
                    params.put("pfirst", nametxt);
                    params.put("plast", lastNametxt);
                    params.put("ssn", soctxt);
                    params.put("address", postxt);
                    params.put("hcname", MedCompany);
                    params.put("hcnum", cardNumbertxt);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                controller.callPostData("post/patient/part1/", params, context);
                (new Handler()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        progress.setProgress(25);
                    }
                }, 1000);

                //Step 2: get pid, aid, hcid
                idsObject = controller.callGetPatientData("patient/"+qrcode+"/"+postxt+"/"+cardNumbertxt, "3Ids", context);
                try {
                    pid = idsObject.getInt("pid");
                    aid = idsObject.getInt("aid");
                    hcid = idsObject.getInt("hcid");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                (new Handler()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                    }
                }, 2000);

                //Step 3: Post part 2
                params = new JSONObject();
                try
                {
                    params.put("pid", pid);
                    params.put("vdate", Calendar.DATE);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                controller.callPostData("post/patient/part2/", params, context);
                (new Handler()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        progress.setProgress(50);
                    }
                }, 1000);

                //Step 4: get vid
                idsObject = controller.callGetPatientData("patients/vid/"+qrcode, "vid", context);
                try {
                    vid = idsObject.getInt("vid");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                (new Handler()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                    }
                }, 2000);

                //Step 5: Post part 3
                params = new JSONObject();
                try
                {
                    params.put("email", emailtxt);
                    params.put("marital", Marital);
                    params.put("gender", gender);
                    params.put("phone", photxt);
                    params.put("weight", weighttxt);
                    params.put("height", heighttxt);
                    params.put("blood", Blood);
                    params.put("pid", pid);
                    params.put("aid", aid);
                    params.put("hcid", hcid);
                    params.put("age", agetxt);
                    params.put("vid", vid);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                controller.callPostData("post/patient/part3/", params, context);
                (new Handler()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        progress.setProgress(75);
                    }
                }, 1000);

                //Step 6: get diagid
                idsObject = controller.callGetPatientData("patient/"+qrcode+"/"+vid, "diagid", context);
                try {
                    diagid = idsObject.getInt("diagid");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                (new Handler()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                    }
                }, 2000);

                //Step 7: Post part 4
                String temp;
                for(int i=0; i<selection.size();i++) {
                    params = new JSONObject();
                    temp = selection.get(i).replace("- ", "");
                    try {
                        params.put("diagid", diagid);
                        params.put("cname", temp);
                        params.put("severity", "High");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    controller.callPostData("post/patient/part4/", params, context);
                    (new Handler()).postDelayed(new Runnable() {
                        @Override
                        public void run() {
                        }
                    }, 1000);
                }
                progress.setProgress(100);
                progress.dismiss();
                startActivity(myIntent);
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

    /*public void FinalSelection( View view) {
        String final_fruit_selection = "";
        for (String selections : selection) {
            final_fruit_selection = final_fruit_selection + selections + "\n";

        }
        final_text.setText(final_fruit_selection);
        final_text.setEnabled(true);
    }*/

   /* public void addListenerOnCheckBoxesSelection() {
        String[] checkBoxes = new
        for(int i =1 ; i>12;i++) {

            if (checkBox.isSelected()) {
                String s = checkBox.getText().toString();
            }
        }
    }*/

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
