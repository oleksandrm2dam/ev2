package com.iesvillaverde.oleksandrm2dam.ventanasej1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class NameActivity extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
        Bundle bundle = getIntent().getExtras();
        textView.setText("Hello " + (String) bundle.get("name"));
    }

    public void goBack(View view) {
        finish();
    }

}
