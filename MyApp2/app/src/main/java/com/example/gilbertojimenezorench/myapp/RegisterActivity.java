package com.example.gilbertojimenezorench.myapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static android.R.attr.button;

public class RegisterActivity extends AppCompatActivity {

    String email, fname, lname, rpass, rcell;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Please Register");
        setContentView(R.layout.activity_register);

        email = ((EditText) findViewById(R.id.remailtxt)).getText().toString();
        fname = ((EditText) findViewById(R.id.fnametxt)).getText().toString();
        lname = ((EditText) findViewById(R.id.lnametxt)).getText().toString();
        rpass = ((EditText) findViewById(R.id.rpasstxt)).getText().toString();
        rcell = ((EditText) findViewById(R.id.rcelltext)).getText().toString();


        Button registerButton = (Button) findViewById(R.id.registerBtn);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(RegisterActivity.this, PaymentActivity.class);
                intent.putExtra("email", email);

                if (email == null) {
                    Toast.makeText(RegisterActivity.this, "All information has to be completed.", Toast.LENGTH_LONG).show();
                }
                else if (fname == null) {
                    Toast.makeText(RegisterActivity.this, "All information has to be completed.", Toast.LENGTH_LONG).show();
                }
                else if (lname == null) {
                    Toast.makeText(RegisterActivity.this, "All information has to be completed.", Toast.LENGTH_LONG).show();
                }
                else if (rpass == null) {
                    Toast.makeText(RegisterActivity.this, "All information has to be completed.", Toast.LENGTH_LONG).show();
                }
                else if (rcell == null) {
                    Toast.makeText(RegisterActivity.this, "All information has to be completed.", Toast.LENGTH_LONG).show();
                }
                else
                {
                    startActivity(intent);
                }


            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
        setResult(RESULT_OK, intent);
        finish();
    }
}
