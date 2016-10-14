package com.example.gilbertojimenezorench.myapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class GeneralUserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general_user);

        Button scanButton = (Button) findViewById(R.id.scanButton);
        scanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentIntegrator intent = new IntentIntegrator(GeneralUserActivity.this);
                intent.initiateScan();

            }
        });


        Button logoutButton = (Button) findViewById(R.id.logOutBtn);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GeneralUserActivity.this, LoginActivity.class);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        Intent loginIntent = getIntent();
        TextView welcomeTxtView = (TextView) findViewById(R.id.welcomeTxtView);
        welcomeTxtView.setText(loginIntent.getStringExtra("user"));

        Toast.makeText(this, "You are logged in.", Toast.LENGTH_LONG).show();

    }

    @Override
    public void onBackPressed() {
        // do nothing.
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
                Intent newScan = new Intent(this, PatientFileActivity.class);
                newScan.putExtra("CONTENTS", contents);
                newScan.putExtra("FORMAT", format);

                startActivity(newScan);
            }

        }
        else{
            finish();
        }
        // else continue with any other code you need in the method

    }
}