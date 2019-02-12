package com.oleksandrm2dam.minesweeper;

import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.Locale;

public class OptionsActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Spinner spinnerLang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);

        spinnerLang = findViewById(R.id.spinnerLang);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.languages, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerLang.setAdapter(adapter);


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String lang;
        switch(position) {
            case 0:
                lang = "en";
                break;
            case 1:
                lang = "es";
                break;
            case 2:
                lang = "ru";
                break;
            default:
                lang = "en";
                break;
        }
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config,
                getBaseContext().getResources().getDisplayMetrics());
        this.recreate();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
