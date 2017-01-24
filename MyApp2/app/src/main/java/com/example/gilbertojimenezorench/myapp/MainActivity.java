package com.example.gilbertojimenezorench.myapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class MainActivity extends AppCompatActivity {

    public ClientController controller;
    public Context context;
    public String qrcode;
    public Patients patient;
    public ProgressDialog progress = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = MainActivity.this;
        controller = ClientController.getInstance();
        progress = new ProgressDialog(context);
        progress.setMessage("Searching...");

        Button scanButton = (Button) findViewById(R.id.scanButton);
        scanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentIntegrator intent = new IntentIntegrator(MainActivity.this);
                intent.initiateScan();

            }
        });

        Button logoutButton = (Button) findViewById(R.id.logOutBtn);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                setResult(RESULT_OK, intent);
                startActivity(intent);
            }
        });

        Button newPatientBtn = (Button) findViewById(R.id.newPatientBtn);
        newPatientBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentIntegrator intent = new IntentIntegrator(MainActivity.this);
                intent.initiateScan();
            }
        });

        Intent loginIntent = getIntent();
        TextView welcomeTxtView = (TextView) findViewById(R.id.welcomeTxtView);
        welcomeTxtView.setText(loginIntent.getStringExtra("user"));

//        Toast.makeText(this, "You are logged in.", Toast.LENGTH_LONG).show();

    }



    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        IntentResult scanResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        if (scanResult != null) {
            String contents = scanResult.getContents();
            String format = scanResult.getFormatName();

            if(contents==null | format == null)
            {
                return;
            }
            else
            {

                qrcode = scanResult.getContents();
                patient = controller.callGetData("patients/"+qrcode, "patients", context);
                progress.show();

                (new Handler()).postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        if(!patient.getQrcode().equals(qrcode)) {
                            Toast.makeText(MainActivity.this, "Patient file wasn't found, please add new patient.", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(MainActivity.this, NewPatientActivity.class);
                            intent.putExtra("qrcode", qrcode);
                            progress.dismiss();
                            //startActivityForResult(intent, 2);
                            startActivity(intent);
                        }
                        else
                        {
                            Intent intent = new Intent(MainActivity.this, DoctorPatientFileActivity.class);
                            intent.putExtra("patient", patient); //to get date call: getIntent().getSerializableExtra("patient");
                            progress.dismiss();
                            //startActivityForResult(intent, 3);
                            startActivity(intent);
                        }

                    }
                }, 5000);
//                for(int i = 0; i<10;i++) {
//                    if(patient != null)
//                        System.out.println(patient.getPfname());
//                }

            }
        }
        else{

        }
        // else continue with any other code you need in the method
    }
}

