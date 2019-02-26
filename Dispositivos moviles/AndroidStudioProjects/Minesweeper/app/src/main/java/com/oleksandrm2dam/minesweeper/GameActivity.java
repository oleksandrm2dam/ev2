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

        switch(getIntent().getExtras().getInt("difficulty")) {
            case 1:
                width = 9;
                height = 9;
                numMines = 10;
                break;
            case 2:
                width = 15;
                height = 15;
                numMines = 20;
                break;
            case 3:
                width = 18;
                height = 18;
                numMines = 30;
                break;
            default:
                width = 9;
                height = 9;
                numMines = 10;
                break;
        }

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
                Intent intent = new Intent(this, OptionsActivity.class);
                intent.putExtra("difficulty", getIntent().getExtras().getInt("difficulty"));
                startActivity(intent);
                finish();
                return true;
            case R.id.go_to_menu:
                intent = new Intent(this, MainActivity.class);
                intent.putExtra("difficulty", getIntent().getExtras().getInt("difficulty"));
                startActivity(intent);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("difficulty", getIntent().getExtras().getInt("difficulty"));
        startActivity(intent);
        finish();
    }

}
