package com.oleksandrm2dam.minesweeper;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import java.util.Locale;

public class OptionsActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Spinner spinnerLang;
    private RadioGroup radioGroup;
    private RadioButton rbNormal, rbHard, rbVeryHard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);

        spinnerLang = findViewById(R.id.spinnerLanguage);
        radioGroup = findViewById(R.id.radioGroup);
        rbNormal = findViewById(R.id.radioNormal);
        rbHard = findViewById(R.id.radioHard);
        rbVeryHard = findViewById(R.id.radioVeryHard);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.lang_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerLang.setAdapter(adapter);
        spinnerLang.setOnItemSelectedListener(this);
    }

    public void onRadioButtonClicked(View view) {
        switch (view.getId()) {
            case R.id.radioNormal:
                System.out.println("normal");
                break;
            case R.id.radioHard:
                System.out.println("h");
                break;
            case R.id.radioVeryHard:
                System.out.println("vh");
                break;
        }
    }

    private void changeLang(String code) {
        Locale locale = new Locale(code);
        Configuration config = new Configuration();
        config.locale = locale;
        getResources().updateConfiguration(config, null);
        Intent refresh = new Intent(OptionsActivity.this, OptionsActivity.class);
        startActivity(refresh);
        finish();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch(position) {
            case 1:
                changeLang("en");
                break;
            case 2:
                changeLang("es");
                break;
            case 3:
                changeLang("ru");
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

}
