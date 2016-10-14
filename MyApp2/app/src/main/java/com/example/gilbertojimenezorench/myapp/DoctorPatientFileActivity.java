package com.example.gilbertojimenezorench.myapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class DoctorPatientFileActivity extends AppCompatActivity {
    Button edit,delete;
    String name,lastName,email,age,phone,physical,postal,social,weight,height,cardNumber;
    TextView nameView, lastNameView, emailView, ageView,phoneView,socialView
            ,physicalView,postalView,weightView,heightView,cardNumberView
            ,maritalView,genderView,bloodView,companyView,diseaseView;
    private String Marital, Blood , MedCompany;
    private RadioGroup rg;
    ArrayList<String> selection= new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_patient_file);

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


        edit = (Button) findViewById(R.id.editBtn);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(DoctorPatientFileActivity.this, NewPatientActivity.class);
                String nametxt = nameView.getText().toString();
                String lastNametxt = lastNameView.getText().toString();
                String emailtxt = emailView.getText().toString();
                String agetxt = ageView.getText().toString();
                String photxt = phoneView.getText().toString();
                String soctxt = socialView.getText().toString();
                String phytxt = physicalView.getText().toString();
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
                myIntent.putExtra("PHYSICAL",phytxt);
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
                Intent intent = new Intent(DoctorPatientFileActivity.this, MainActivity.class);
                Toast.makeText(DoctorPatientFileActivity.this, "File has been deleted", Toast.LENGTH_LONG).show();
                startActivity(intent);
            }
        });

        Button close = (Button) findViewById(R.id.doctorClosingBtn);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DoctorPatientFileActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
