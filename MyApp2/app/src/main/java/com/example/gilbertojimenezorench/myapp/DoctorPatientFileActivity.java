package com.example.gilbertojimenezorench.myapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;

public class DoctorPatientFileActivity extends AppCompatActivity {
    Button edit,delete;
    String name,lastName,email,phone,postal,social,weight,height,cardNumber, gender, qrcode;
    TextView nameView, lastNameView, emailView, birthView,phoneView,socialView
            ,postalView,weightView,heightView,cardNumberView
            ,maritalView,genderView,bloodView,companyView,diseaseView;
    int age;
    private String Marital, Blood , MedCompany;
    private RadioGroup rg;
    ArrayList<String> selection= new ArrayList<String>();
    Patients patient;
    ClientController controller;
    Context context = this;
    JSONObject params;
    ProgressDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_patient_file);

        patient = (Patients) getIntent().getExtras().getSerializable("patient");

        controller = ClientController.getInstance();

        nameView = (TextView) findViewById(R.id.fnameView);
        lastNameView = (TextView) findViewById(R.id.lnameView);
        emailView = (TextView) findViewById(R.id.remailView);
        birthView = (TextView) findViewById(R.id.birthDate);
        phoneView = (TextView) findViewById(R.id.rcellView);
        socialView = (TextView) findViewById(R.id.socialNumberView);
        postalView = (TextView) findViewById(R.id.postalAddressView);
        weightView = (TextView) findViewById(R.id.weightView);
        heightView = (TextView) findViewById(R.id.heightView);
        cardNumberView = (TextView) findViewById(R.id.cardNumberMedView);

        maritalView = (TextView) findViewById(R.id.maritalSelection);
        genderView = (TextView) findViewById(R.id.genderSelection);
        bloodView = (TextView) findViewById(R.id.bloodSelection);
        companyView = (TextView) findViewById(R.id.medSelection);
        diseaseView = (TextView) findViewById(R.id.recordSelection);

        ArrayList<String> myList = (ArrayList<String>) getIntent().getSerializableExtra("SELECT");

        String bloodtype = getIntent().getStringExtra("BLOOD");
        String maritaltype = getIntent().getStringExtra("MARITAL");
        String medicaretype = getIntent().getStringExtra("MEDCOM");
        String conditionSelection = "";

        progress = new ProgressDialog(context);
        progress.setMessage("Deleting...");

        //Set Extras from Database retrieved info
        if(patient!=null) {
            name = patient.getPfname();
            lastName = patient.getPlname();
            email = patient.getInfo().getEmail();
            age = patient.getInfo().getAge();
            phone = patient.getInfo().getPhone();
            social = patient.getSsn();
            postal = patient.getInfo().getAddressInfo().getAddress();
            weight = patient.getInfo().getWeight();
            height = patient.getInfo().getHeight();
            cardNumber = patient.getInfo().getHealth().getHcnum();
            gender = patient.getInfo().getGender();
            qrcode = patient.getQrcode();
        }
        else
        {
            //Set Extras from new patient activity
            name = getIntent().getStringExtra("NAME");
            lastName = getIntent().getStringExtra("LASTNAME");
            email = getIntent().getStringExtra("EMAIL");
            age = getIntent().getIntExtra("AGE", 30);
            phone = getIntent().getStringExtra("PHONE");
            social = getIntent().getStringExtra("SOCIAL");
            postal = getIntent().getStringExtra("POSTAL");
            weight = getIntent().getStringExtra("WEIGHT");
            height = getIntent().getStringExtra("HEIGHT");
            cardNumber = getIntent().getStringExtra("CARDNUM");
            gender = getIntent().getStringExtra("RADIO");
            qrcode = getIntent().getStringExtra("qrcode");
        }

        // Set Text
        nameView.setText(name);
        lastNameView.setText(lastName);
        emailView.setText(email);
        birthView.setText(String.valueOf(age));
        phoneView.setText(phone);
        socialView.setText(social);
        postalView.setText(postal);
        weightView.setText(weight);
        heightView.setText(height);
        cardNumberView.setText(cardNumber);
        genderView.setText(gender);

        if(getIntent().hasExtra("patient"))
        {
            maritalView.setText(patient.getInfo().getMstatus());
            genderView.setText(patient.getInfo().getGender());
            bloodView.setText(patient.getInfo().getBlood());
            companyView.setText(patient.getInfo().getHealth().getHcname());
            String conditionNames="";
            if(!patient.getVisits().isEmpty()) {
                for (Conditions condition : patient.getVisits().get(0).getDiagnostic()) {
                    conditionNames += "- " + condition.getCondName();
                    diseaseView.setText(conditionNames);
                    conditionNames += "\n\n";
                }
                diseaseView.setText(conditionNames+"\n");
            }
        }
        else {
            for (String selections : myList) {conditionSelection = conditionSelection + selections + "\n";}
            maritalView.setText(maritaltype);
            bloodView.setText(bloodtype);
            companyView.setText(medicaretype);
            diseaseView.setText(conditionSelection);
        }




        edit = (Button) findViewById(R.id.editBtn);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(DoctorPatientFileActivity.this, NewPatientActivity.class);
                String nametxt = nameView.getText().toString();
                String lastNametxt = lastNameView.getText().toString();
                String emailtxt = emailView.getText().toString();
                String agetxt = birthView.getText().toString();
                String photxt = phoneView.getText().toString();
                String soctxt = socialView.getText().toString();
                String postxt = postalView.getText().toString();
                String weighttxt = weightView.getText().toString();
                String heighttxt = heightView.getText().toString();
                String cardNumbertxt = cardNumberView.getText().toString();

//                String selectedRadioValue = ((RadioButton)findViewById(rg.getCheckedRadioButtonId())).getText().toString();

                myIntent.putExtra("BLOOD",Blood);
                myIntent.putExtra("MEDCOM",MedCompany);
                myIntent.putExtra("MARITAL",Marital);
                myIntent.putExtra("NAME",nametxt);
                myIntent.putExtra("LASTNAME",lastNametxt);
                myIntent.putExtra("EMAIL",emailtxt);
                myIntent.putExtra("AGE",agetxt);
                myIntent.putExtra("PHONE",photxt);
                myIntent.putExtra("SOCIAL",soctxt);
                myIntent.putExtra("POSTAL",postxt);
                myIntent.putExtra("WEIGHT",weighttxt);
                myIntent.putExtra("HEIGHT",heighttxt);
                myIntent.putExtra("CARDNUM",cardNumbertxt);
//                myIntent.putStringArrayListExtra("SELECT",selection);
//                myIntent.putExtra("RADIO",selectedRadioValue);

                startActivity(myIntent);
            }
        });



        delete = (Button) findViewById(R.id.deleteBtn);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                controller.callDeleteData("delete/patient/"+qrcode, context);
                progress.show();
                (new Handler()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(DoctorPatientFileActivity.this, MainActivity.class);
                        progress.dismiss();
                        Toast.makeText(DoctorPatientFileActivity.this, "File has been deleted", Toast.LENGTH_LONG).show();
                        controller.reset();
                        startActivity(intent);
                    }
                }, 2000);
            }
        });

        Button close = (Button) findViewById(R.id.doctorClosingBtn);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DoctorPatientFileActivity.this, MainActivity.class);
                controller.reset();
                startActivity(intent);
            }
        });
    }
}
