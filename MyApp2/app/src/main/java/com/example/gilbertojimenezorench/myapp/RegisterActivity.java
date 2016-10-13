package com.example.gilbertojimenezorench.myapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import static android.R.attr.button;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Please Register");
        setContentView(R.layout.activity_register);

        Button registerButton = (Button) findViewById(R.id.registerBtn);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, PaymentActivity.class);
                String email = ((EditText) findViewById(R.id.remailtxt)).getText().toString();
                intent.putExtra("email", email);
                startActivity(intent);
            }
        });
    }
}
