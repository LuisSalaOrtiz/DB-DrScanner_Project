package com.example.gilbertojimenezorench.myapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class NewPatientActivity extends AppCompatActivity {

    private Spinner spinnerMarital, spinnerBlood, spinnerMedicare;
    private Button submit;
    private EditText name,lastName,email,age,phoNumber,phyAddress,posAddress,socNumber,weight,height,cardNumber;
    private String Marital, Blood , MedCompany;
    private RadioGroup rg;
    ArrayList<String> selection= new ArrayList<String>();


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

        rg = (RadioGroup) findViewById(R.id.radioGroup);



        addListenerOnSpinnerItemSelection1();
        addListenerOnSpinnerItemSelection2();
        addListenerOnSpinnerItemSelection3();



        submit = (Button) findViewById(R.id.submitBtn);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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


                Intent myIntent = new Intent(NewPatientActivity.this,DoctorPatientFileActivity.class);

//                if(rg!=null)
//                {
//                    String selectedRadioValue = ((RadioButton)findViewById(rg.getCheckedRadioButtonId())).getText().toString();
//                    myIntent.putExtra("RADIO",selectedRadioValue);
//                }
//                else
//                {
//                    Toast.makeText(NewPatientActivity.this, "All information must be completed.", Toast.LENGTH_LONG).show();
//                }



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

                try
                {
                    String selectedRadioValue = ((RadioButton)findViewById(rg.getCheckedRadioButtonId())).getText().toString();
                    myIntent.putExtra("RADIO",selectedRadioValue);
                    startActivity(myIntent);
                } catch(NullPointerException e)
                {
                    Toast.makeText(NewPatientActivity.this, "All information must be completed.", Toast.LENGTH_LONG).show();
                }

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
