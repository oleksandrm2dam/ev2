package com.oleksandrm2dam.minesweeper;

import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Chronometer;
import android.widget.TextView;

public class GameActivity extends AppCompatActivity {

    private MinesweeperView minesweeperView;
    private int width, height, numMines;
    private TextView flagCounter;
    private Chronometer chronometer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        width = 9;
        height = 9;
        numMines = 10;

        minesweeperView = findViewById(R.id.minesweeperView);
        minesweeperView.startNewGame(width, height, numMines);

        flagCounter = findViewById(R.id.textViewFlagCounter);
        minesweeperView.setFlagCounter(flagCounter);
        minesweeperView.updateFlagCounter();

        chronometer = findViewById(R.id.simpleChronometer);
        minesweeperView.setChronometer(chronometer);
        chronometer.start();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_game_activity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.new_game:
                minesweeperView.startNewGame(width, height, numMines);
                minesweeperView.updateFlagCounter();
                chronometer.setBase(SystemClock.elapsedRealtime());
                chronometer.start();
                return true;
            case R.id.restart_game:
                minesweeperView.restartGame();
                minesweeperView.updateFlagCounter();
                chronometer.setBase(SystemClock.elapsedRealtime());
                chronometer.start();
                return true;
            case R.id.change_lenguage:
                startActivity(new Intent(this, OptionsActivity.class));
                return true;
            case R.id.go_to_menu:
                startActivity(new Intent(this, MainActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
