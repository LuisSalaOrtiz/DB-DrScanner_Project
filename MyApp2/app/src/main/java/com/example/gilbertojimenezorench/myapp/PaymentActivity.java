package com.example.gilbertojimenezorench.myapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class PaymentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Secure Payment Info");
        setContentView(R.layout.activity_payment);


        Button submitBtn = (Button) findViewById(R.id.submitPaymentBtn);
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PaymentActivity.this, PaymentConfirmationActivity.class);
                intent.putExtra("email", getIntent().getStringExtra("email"));

                String name = ((EditText) findViewById(R.id.visaNameTxt)).getText().toString();
                intent.putExtra("name", name);

                String card = ((EditText) findViewById(R.id.cardNumberTxt)).getText().toString();
                intent.putExtra("card", card);

                if(name==null||card==null)
                {
                    Toast.makeText(PaymentActivity.this, "All information has to be completed.", Toast.LENGTH_LONG).show();
                }
                else if(card.length()<16||card.length()>16)
                {
                    Toast.makeText(PaymentActivity.this, "Card number must have 16 digits.", Toast.LENGTH_LONG).show();
                }
                else
                {
                    startActivity(intent);
                }
            }
        });

        Button cancelBtn = (Button) findViewById(R.id.cancelPayBtn);
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PaymentActivity.this, LoginActivity.class);
                setResult(RESULT_CANCELED,intent);
                startActivity(intent);
            }
        });
    }
}
