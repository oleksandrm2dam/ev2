package com.iesvillaverde.oleksandrm2dam.notificacionesej1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText editTextName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextName = findViewById(R.id.editText);
    }

    public void notificate(View view) {
        String name = editTextName.getText().toString();
        String sentence = "Hello " + name + "!";
        Toast notification = Toast.makeText(this, sentence, Toast.LENGTH_LONG);
        notification.show();
    }
}
