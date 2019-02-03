package com.iesvillaverde.oleksandrm2dam.ventanasej1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class NameActivity extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name);
        textView = findViewById(R.id.textView);
        Bundle bundle = getIntent().getExtras();
        String name = bundle.getString("name");
        textView.setText("Hello " + name + "!");
    }

    public void closeActivity(View view) {
        finish();
    }
    
}
