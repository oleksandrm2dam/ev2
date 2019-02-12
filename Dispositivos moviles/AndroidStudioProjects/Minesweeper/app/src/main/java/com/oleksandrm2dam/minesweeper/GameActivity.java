package com.oleksandrm2dam.minesweeper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class GameActivity extends AppCompatActivity {

    private MinesweeperView minesweeperView;
    private int width, height;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Bundle bundle = getIntent().getExtras();
        //width = bundle.getInt("width");
        //height = bundle.getInt("height");
        width = 9;
        height = 9;

        minesweeperView = findViewById(R.id.minesweeperView);
        minesweeperView.startNewGame(width, height);
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
                minesweeperView.startNewGame(width, height);
                return true;
            case R.id.restart_game:
                minesweeperView.restartGame();
                return true;
            case R.id.change_lenguage:

                return true;
            case R.id.go_to_menu:

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
