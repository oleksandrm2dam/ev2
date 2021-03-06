package com.oleksandrm2dam.minesweeper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private int difficulty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            difficulty = getIntent().getExtras().getInt("difficulty");
        } catch (NullPointerException e) {
            difficulty = 1;
        }

    }

    public void newGame(View view) {
        Intent gameActivity = new Intent(this, GameActivity.class);
        gameActivity.putExtra("difficulty", difficulty);
        startActivity(gameActivity);
    }

    public void options(View view) {
        Intent optionsActivity = new Intent(this, OptionsActivity.class);
        optionsActivity.putExtra("difficulty", difficulty);
        startActivity(optionsActivity);
    }

    public void about(View view) {
        startActivity(new Intent(this, AboutActivity.class));
    }

    public void exit(View view) {
        moveTaskToBack(true);
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);
    }
}
