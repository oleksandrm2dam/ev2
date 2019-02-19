package com.oleksandrm2dam.minesweeper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button buttonContinueGame;
    private int optWidth, optHeight, optNumMines;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonContinueGame = findViewById(R.id.buttonContinueGame);
        setOptHeight(9);
        setOptWidth(9);
        setOptNumMines(10);
    }

    public void continueGame(View view) {
        finish();
    }

    public void newGame(View view) {
        Intent gameActivity = new Intent(this, GameActivity.class);
        gameActivity.putExtra("width", optWidth);
        gameActivity.putExtra("height", optHeight);
        gameActivity.putExtra("numMines", optNumMines);
        startActivity(gameActivity);
    }

    public void options(View view) {
        Intent optionsActivity = new Intent(this, OptionsActivity.class);
        optionsActivity.putExtra("width", optWidth);
        startActivity(optionsActivity);
    }

    public void about(View view) {
        finish();
    }

    public void exit(View view) {
        finish();
    }

    public int getOptWidth() {
        return optWidth;
    }

    public void setOptWidth(int optWidth) {
        this.optWidth = optWidth;
    }

    public int getOptHeight() {
        return optHeight;
    }

    public void setOptHeight(int optHeight) {
        this.optHeight = optHeight;
    }

    public int getOptNumMines() {
        return optNumMines;
    }

    public void setOptNumMines(int optNumMines) {
        this.optNumMines = optNumMines;
    }
}
