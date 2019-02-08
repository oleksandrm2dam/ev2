package com.oleksandrm2dam.actionbarstrings;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.option1:
                Toast.makeText(this, R.string.menu_option1, Toast.LENGTH_LONG).show();
                return true;
            case R.id.option2:
                Toast.makeText(this, R.string.menu_option2, Toast.LENGTH_LONG).show();
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
}
