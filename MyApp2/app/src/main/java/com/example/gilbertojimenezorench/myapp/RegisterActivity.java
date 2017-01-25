package com.example.gilbertojimenezorench.myapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import static android.R.attr.button;

public class RegisterActivity extends AppCompatActivity {

    String email, fname, lname, rpass, rcell, type;
    public ClientController controller;
    public Context context;
    private RadioButton radio1, radio2;
    public ProgressDialog progress = null;
    JSONObject params;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Please Register");
        setContentView(R.layout.activity_register);

        params = new JSONObject();

        controller = ClientController.getInstance();
        context = RegisterActivity.this;

        email = ((EditText) findViewById(R.id.remailtxt)).getText().toString();
        fname = ((EditText) findViewById(R.id.fnametxt)).getText().toString();
        lname = ((EditText) findViewById(R.id.lnametxt)).getText().toString();
        rpass = ((EditText) findViewById(R.id.rpasstxt)).getText().toString();
        rcell = ((EditText) findViewById(R.id.rcelltext)).getText().toString();
        radio1 = (RadioButton) findViewById(R.id.adminRadioBtn);
        radio2 = (RadioButton) findViewById(R.id.nurseRadioBtn);
        type="general";

        progress = new ProgressDialog(context);
        progress.setMessage("Verifying for register...");



        Button registerButton = (Button) findViewById(R.id.registerBtn);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                email = ((EditText) findViewById(R.id.remailtxt)).getText().toString();
                fname = ((EditText) findViewById(R.id.fnametxt)).getText().toString();
                lname = ((EditText) findViewById(R.id.lnametxt)).getText().toString();
                rpass = ((EditText) findViewById(R.id.rpasstxt)).getText().toString();
                rcell = ((EditText) findViewById(R.id.rcelltext)).getText().toString();
                type="general";

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
                    RadioGroup g = (RadioGroup) findViewById(R.id.typeRadioGroup);

// Returns an integer which represents the selected radio button's ID
                    int selectedID = g.getCheckedRadioButtonId();

// Gets a reference to our "selected" radio button
                    RadioButton b = (RadioButton) findViewById(selectedID);

// Now you can get the text or whatever you want from the "selected" radio button
                    type = b.getText().toString();

                    try {
                        params.put("password",rpass);
                        params.put("type",type);
                        params.put("email",email);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    controller.callPostData("post/user/", params, context);
                    progress.show();

                    (new Handler()).postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent intent = new Intent(RegisterActivity.this, PaymentActivity.class);
                            intent.putExtra("email", email);
                            progress.dismiss();
                            startActivity(intent);
                        }
                    }, 3000);

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
