package com.example.gilbertojimenezorench.myapp;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class PaymentConfirmationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_confirmation);

        TextView name = (TextView) findViewById(R.id.fullNameTextView);
        name.setText(getIntent().getStringExtra("name"));

        TextView card = (TextView) findViewById(R.id.cardNumTextView);
        card.setText("  XXXXXXXXXXXX"+ getIntent().getStringExtra("card").subSequence(12, 16));

        TextView amount = (TextView) findViewById(R.id.totalTextView);
        amount.setText("$1.00");


        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("dd/MMM/yyyy");
        String formattedDate = df.format(c.getTime());

        TextView date = (TextView) findViewById(R.id.actualDateTextView);
        date.setText(formattedDate);


        Button closeBtn = (Button) findViewById(R.id.closingBtn);
        closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PaymentConfirmationActivity.this, LoginActivity.class);
                intent.putExtra("email", getIntent().getStringExtra("email"));
                setResult(Activity.RESULT_OK,intent);
                startActivity(intent);
            }
        });
    }
}
