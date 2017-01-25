package com.example.gilbertojimenezorench.myapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class PatientFileActivity extends AppCompatActivity {
    String name,lastName,email,phone,postal,social,weight,height,cardNumber;
    TextView nameView, lastNameView, emailView, birthView,phoneView,socialView
            ,postalView,weightView,heightView,cardNumberView
            ,maritalView,genderView,bloodView,companyView,diseaseView;
    int age;
    private String Marital, Blood , MedCompany;
    private RadioGroup rg;
    ArrayList<String> selection= new ArrayList<String>();
    Patients patient;
    ClientController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_file);

        patient = (Patients) getIntent().getExtras().getSerializable("patient");
        controller = ClientController.getInstance();

        nameView = (TextView) findViewById(R.id.fnameView);
        lastNameView = (TextView) findViewById(R.id.lnameView);
        emailView = (TextView) findViewById(R.id.remailView);
        birthView = (TextView) findViewById(R.id.ageView);
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

        //Set Extras from Database retrieved info
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
            diseaseView.setText(conditionNames);
        }


        Button close = (Button) findViewById(R.id.patientClosingBtn);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PatientFileActivity.this, GeneralUserActivity.class);
                patient=null;
                controller.reset();
                startActivity(intent);
            }
        });
    }
}
