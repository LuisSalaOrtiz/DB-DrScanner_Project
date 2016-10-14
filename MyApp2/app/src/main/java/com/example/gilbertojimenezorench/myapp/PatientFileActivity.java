package com.example.gilbertojimenezorench.myapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class PatientFileActivity extends AppCompatActivity {
    String name,lastName,email,age,phone,physical,postal,social,weight,height,cardNumber;
    TextView nameView, lastNameView, emailView, ageView,phoneView,socialView
            ,physicalView,postalView,weightView,heightView,cardNumberView
            ,maritalView,genderView,bloodView,companyView,diseaseView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_file);

        nameView = (TextView) findViewById(R.id.fnameView);
        lastNameView = (TextView) findViewById(R.id.lnameView);
        emailView = (TextView) findViewById(R.id.remailView);
        ageView = (TextView) findViewById(R.id.ageView);
        phoneView = (TextView) findViewById(R.id.rcellView);
        socialView = (TextView) findViewById(R.id.socialNumberView);
        physicalView = (TextView) findViewById(R.id.physicalAddressView);
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

        String selectedRadioValue = getIntent().getStringExtra("RADIO");
        String bloodtype = getIntent().getStringExtra("BLOOD");
        String maritaltype = getIntent().getStringExtra("MARITAL");
        String medicaretype = getIntent().getStringExtra("MEDCOM");

        String conditionSelection = "";
        for (String selections : myList) {conditionSelection = conditionSelection + selections + "\n";}

        //Set Extras
        name = getIntent().getStringExtra("NAME");
        lastName = getIntent().getStringExtra("LASTNAME");
        email = getIntent().getStringExtra("EMAIL");
        age = getIntent().getStringExtra("AGE");
        phone = getIntent().getStringExtra("PHONE");
        social = getIntent().getStringExtra("SOCIAL");
        physical = getIntent().getStringExtra("PHYSICAL");
        postal = getIntent().getStringExtra("POSTAL");
        weight = getIntent().getStringExtra("WEIGHT");
        height = getIntent().getStringExtra("HEIGHT");
        cardNumber = getIntent().getStringExtra("CARDNUM");

        // Set Text
        nameView.setText(name);
        lastNameView.setText(lastName);
        emailView.setText(email);
        ageView.setText(age);
        phoneView.setText(phone);
        socialView.setText(social);
        physicalView.setText(physical);
        postalView.setText(postal);
        weightView.setText(weight);
        heightView.setText(height);
        cardNumberView.setText(cardNumber);

        maritalView.setText(maritaltype);
        genderView.setText(selectedRadioValue);
        bloodView.setText(bloodtype);
        companyView.setText(medicaretype);
        diseaseView.setText(conditionSelection);


        Button close = (Button) findViewById(R.id.patientClosingBtn);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
