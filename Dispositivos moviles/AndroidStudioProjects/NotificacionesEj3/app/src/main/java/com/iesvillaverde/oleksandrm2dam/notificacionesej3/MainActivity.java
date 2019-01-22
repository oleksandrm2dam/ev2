package com.iesvillaverde.oleksandrm2dam.notificacionesej3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private EditText editTextNumber;
    private Button button;
    private TextView textView;
    private Toast notification;
    private Random random;
    private int generatedNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextNumber = findViewById(R.id.editText);
        button = findViewById(R.id.button);
        textView = findViewById(R.id.textView);
        random = new Random();
        resetGame();
    }

    public void click(View view) {
        if(button.getText().equals("Guess")) {
            guess();
        } else {
            resetGame();
        }
    }

    private void guess() {
        String answer;
        try {
            int numberUser = Integer.parseInt(editTextNumber.getText().toString());
            if(generatedNumber == numberUser) {
                answer = "You are right! The correct number is " + generatedNumber;
                button.setText("New game");
            } else {
                if(generatedNumber < numberUser) {
                    answer = "The correct number is smaller than your guess!";
                } else {
                    answer = "The correct number is bigger than your guess!";
                }
            }
        } catch (NumberFormatException e) {
            answer = "ERROR: Number format";
        }
        notification = Toast.makeText(this, answer, Toast.LENGTH_LONG);
    }

    private void resetGame() {
        generatedNumber = random.nextInt(100) + 1;
        button.setText("Guess");
    }
}
