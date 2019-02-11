package com.oleksandrm2dam.minesweeper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button buttonContinueGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonContinueGame = findViewById(R.id.buttonContinueGame);

    }

    public void continueGame(View view) {
        finish();
    }

    public void newGame(View view) {
        Intent gameActivity = new Intent(this, GameActivity.class);
        startActivity(gameActivity);
    }

    public void options(View view) {
        finish();
    }

    public void about(View view) {
        finish();
    }

    public void exit(View view) {
        finish();
    }

}
