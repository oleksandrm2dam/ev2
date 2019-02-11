package com.oleksandrm2dam.minesweeper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

public class GameActivity extends AppCompatActivity {

    private MinesweeperView minesweeperView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        minesweeperView = findViewById(R.id.minesweeperView);
    }
}
