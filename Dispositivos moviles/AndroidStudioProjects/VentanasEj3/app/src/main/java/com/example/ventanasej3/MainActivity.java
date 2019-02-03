package com.example.ventanasej3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText editTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextPassword = findViewById(R.id.editText);
    }

    public void checkPassword(View view) {
        String password = editTextPassword.getText().toString();
        if(!password.isEmpty()) {
            if(password.equals("Villaverde")) {
                changeActivity();
            } else {
                Toast notification = Toast.makeText(this, "Incorrect password", Toast.LENGTH_LONG);
                notification.show();
            }
        }
    }

    public void changeActivity() {
        Intent welcomeActivity = new Intent(this, WelcomeActivity.class);
        startActivity(welcomeActivity);
    }
}
