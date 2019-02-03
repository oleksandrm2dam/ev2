package com.example.ventanasej4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        textView = findViewById(R.id.textView);
        Bundle bundle = getIntent().getExtras();
        int number = bundle.getInt("number");
        boolean isPrime = bundle.getBoolean("isPrime");
        boolean isEven = bundle.getBoolean("isEven");
        String text = "The number " + number;
        if(isEven) {
            text += " is even";
        } else {
            text += " is odd";
        }
        if(isPrime) {
            text += " and prime!";
        } else {
            text += " and not prime!";
        }
        textView.setText(text);
    }
}
