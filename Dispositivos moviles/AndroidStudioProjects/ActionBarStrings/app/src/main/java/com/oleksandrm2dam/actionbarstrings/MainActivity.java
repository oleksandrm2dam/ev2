package com.oleksandrm2dam.actionbarstrings;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private Button buttonHideShowAB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonHideShowAB = findViewById(R.id.button);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.optionSpanish:
                changeLang("es");
                return true;
            case R.id.optionEnglish:
                changeLang("en");
                return true;
            case R.id.phone:
                Toast.makeText(this, R.string.phone, Toast.LENGTH_LONG).show();
                return true;
            case R.id.camera:
                Toast.makeText(this, R.string.camera, Toast.LENGTH_LONG).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void changeLang(String code) {
        Locale locale = new Locale(code);
        Configuration config = new Configuration();
        config.locale = locale;
        getResources().updateConfiguration(config, null);
        Intent refresh = new Intent(MainActivity.this, MainActivity.class);
        startActivity(refresh);
        finish();
    }

    public void showHideAB(View view) {
        if(getSupportActionBar().isShowing()) {
            getSupportActionBar().hide();
            buttonHideShowAB.setText(R.string.show_action_bar);
        } else {
            getSupportActionBar().show();
            buttonHideShowAB.setText(R.string.hide_action_bar);
        }
    }

}
