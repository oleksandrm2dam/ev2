package com.iesvillaverde.oleksandrm2dam.notificacionesej2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    EditText editTextNumber;
    int generatedNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextNumber = findViewById(R.id.editText);
        generatedNumber = new Random().nextInt(100000) + 1;
        Toast notification = Toast.makeText(this, Integer.toString(generatedNumber), Toast.LENGTH_LONG);
        notification.show();
    }

    public void check(View view) {
        String sentence;
        int number = Integer.parseInt(editTextNumber.getText().toString());

        if(number == generatedNumber) {
            sentence = "You are right!!!!";
        } else {
            sentence = "You are wrong. :-(";
        }

        Toast notification = Toast.makeText(this, sentence, Toast.LENGTH_LONG);
        notification.show();
    }

}
