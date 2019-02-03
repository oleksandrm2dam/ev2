package com.example.ventanasej4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText editTextNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextNumber = findViewById(R.id.editText);
    }

    public void analize(View view) {
        try {
            int number = Integer.parseInt(editTextNumber.getText().toString());
            changeActivity(number, isPrime(number), number % 2 == 0);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    public void changeActivity(int number, boolean isPrime, boolean isEven) {
        Intent resultActivity = new Intent(this, ResultActivity.class);
        resultActivity.putExtra("number", number);
        resultActivity.putExtra("isPrime", isPrime);
        resultActivity.putExtra("isEven", isEven);
        startActivity(resultActivity);
    }

    private boolean isPrime(int number) {
        for(int i = 2; i < number; ++i) {
            if(number % i == 0) {
                return false;
            }
        }
        return true;
    }

}
