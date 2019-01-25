package com.iesvillaverde.oleksandrm2dam.ventanasej1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.editText);
    }

    public void changeActivity(View view) {
        String name = editText.getText().toString();
        Intent nameActivity = new Intent(this, NameActivity.class);
        nameActivity.putExtra("name", name);
        startActivity(nameActivity);
    }
}
