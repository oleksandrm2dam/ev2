package com.oleksandrm2dam.minesweeper;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public class MinesweeperView extends View {

    private MineField mineField;

    private int tileSize;
    private float halfTileSize;

    private Paint paintNumber, paintBgUnchecked, paintBgChecked, paintBorder, paintBomb,
            paintBgFlagged;

    private long lastActionDown;
    private final static long LONG_CLICK_TIME = 200;
    private int numFlaggedTiles;

    private boolean gameOver;
    private boolean gameWon;

    public MinesweeperView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaints();
    }

    public void startNewGame(int width, int height) {
        mineField = new MineField(width, height);
        numFlaggedTiles = 0;
        gameOver = false;
        gameWon = false;
        invalidate();
    }

    public void restartGame() {
        for(int i = 0; i < mineField.getWidth(); ++i) {
            for(int j = 0; j < mineField.getHeight(); ++j) {
                mineField.getTiles()[i][j].setChecked(false);
                mineField.getTiles()[i][j].setFlagged(false);
            }
        }
        mineField.setNumCheckedTiles(0);
        numFlaggedTiles = 0;
        gameOver = false;
        gameWon = false;
        invalidate();
    }

    private void initPaints() {
        paintBgUnchecked = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintBgUnchecked.setColor(Color.GRAY);
        paintBgUnchecked.setStyle(Paint.Style.FILL);

        paintBgChecked = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintBgChecked.setColor(Color.WHITE);
        paintBgChecked.setStyle(Paint.Style.FILL);

        paintBorder = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintBorder.setColor(Color.BLACK);
        paintBorder.setStyle(Paint.Style.STROKE);

        paintNumber = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintNumber.setColor(Color.RED);
        paintNumber.setTextSize(75);
        paintNumber.setStyle(Paint.Style.FILL);
        paintNumber.setTextAlign(Paint.Align.CENTER);

        paintBomb = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintBomb.setColor(Color.RED);
        paintBomb.setStyle(Paint.Style.FILL);

        paintBgFlagged = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintBgFlagged.setColor(Color.YELLOW);
        paintBgFlagged.setStyle(Paint.Style.FILL);
    }

    @Override
    public void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        tileSize = w / mineField.getWidth();
        halfTileSize = tileSize / 2F;
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawTiles(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(gameOver || gameWon) return false;
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastActionDown = event.getDownTime();
                return true;
            case MotionEvent.ACTION_UP:
                int i = (int) event.getX() / tileSize;
                int j = (int) event.getY() / tileSize;
                if(i >= 0 && i < mineField.getWidth() && j >= 0 && j < mineField.getHeight()) {
                    if(event.getEventTime() - lastActionDown < LONG_CLICK_TIME) {
                        shortClick(i, j);
                    } else {
                        longClick(i, j);
                    }
                }
                return true;
            default:
                return false;
        }
    }

    private void shortClick(int i, int j) {
        Tile clickedTile = mineField.getTiles()[i][j];
        if(clickedTile.isChecked() || clickedTile.isFlagged()) {
            return;
        }
        clickedTile.setChecked(true);
        mineField.setNumCheckedTiles(1 + mineField.getNumCheckedTiles());
        if(clickedTile.hasMine()) {
            bombClick();
        } else {
            if(clickedTile.getNumberOfMinesAround() == 0) {
                mineField.checkEmptyTile(i, j);
            }
            if(mineField.getNumCheckedTiles() == mineField.getTotalNumTiles()
                    - mineField.getNumberOfMines()) {
                winGame();
            }
        }
        invalidate();
    }

    private void longClick(int i, int j) {
        Tile clickedTile = mineField.getTiles()[i][j];
        if(!clickedTile.isChecked()) {
            if(clickedTile.isFlagged()) {
                clickedTile.setFlagged(false);
                --numFlaggedTiles;
            } else {
                if(numFlaggedTiles < mineField.getNumberOfMines()) {
                    clickedTile.setFlagged(true);
                    ++numFlaggedTiles;
                }
            }
            invalidate();
        }
    }

    private void bombClick() {
        for(int i = 0; i < mineField.getWidth(); ++i) {
            for(int j = 0; j < mineField.getHeight(); ++j) {
                if(mineField.getTiles()[i][j].hasMine()) {
                    mineField.getTiles()[i][j].setChecked(true);
                }
            }
        }
        gameOver = true;
        Toast.makeText(this.getContext(), R.string.game_over, Toast.LENGTH_LONG).show();
    }

    private void winGame() {
        gameWon = true;
        Toast.makeText(this.getContext(), R.string.game_won, Toast.LENGTH_LONG).show();
    }

    private void drawTiles(Canvas canvas) {
        for(int i = 0; i < mineField.getWidth(); ++i) {
            for(int j = 0; j < mineField.getHeight(); ++j) {
                if(mineField.getTiles()[i][j].isChecked()) {
                    if(mineField.getTiles()[i][j].hasMine()) {
                        // Draw mine
                        canvas.drawRect(i * tileSize, j * tileSize,
                                i * tileSize + tileSize, j * tileSize + tileSize, paintBomb);
                    } else {
                        // Draw checked background
                        canvas.drawRect(i * tileSize, j * tileSize,
                                i * tileSize + tileSize, j * tileSize + tileSize, paintBgChecked);
                        // Draw number
                        paintNumber.setColor(getNumberColor(mineField.getTiles()[i][j]));
                        canvas.drawText(Integer.toString(mineField.getTiles()[i][j].getNumberOfMinesAround()),
                                i * tileSize + halfTileSize, j * tileSize + tileSize, paintNumber);
                    }
                } else {
                    if(mineField.getTiles()[i][j].isFlagged()) {
                        // Draw flagged background
                        canvas.drawRect(i * tileSize, j * tileSize,
                                i * tileSize + tileSize, j * tileSize + tileSize, paintBgFlagged);
                    } else {
                        // Draw unchecked background
                        canvas.drawRect(i * tileSize, j * tileSize,
                                i * tileSize + tileSize, j * tileSize + tileSize, paintBgUnchecked);
                    }
                }
                // Draw border
                canvas.drawRect(i * tileSize, j * tileSize,
                        i * tileSize + tileSize, j * tileSize + tileSize, paintBorder);
            }
        }
    }

    private int getNumberColor(Tile tile) {
        switch(tile.getNumberOfMinesAround()) {
            case 1:
                return Color.BLUE;
            case 2:
                return Color.GREEN;
            case 3:
                return Color.RED;
            case 4:
                return Color.rgb(128, 0, 128); // Purple
            case 5:
                return Color.rgb(128, 0, 0); // Maroon
            case 6:
                return Color.rgb(0, 206, 209); // Turquoise
            case 7:
                return Color.BLACK;
            case 8:
                return Color.GRAY;
            default:
                return Color.WHITE;
        }
    }

}
