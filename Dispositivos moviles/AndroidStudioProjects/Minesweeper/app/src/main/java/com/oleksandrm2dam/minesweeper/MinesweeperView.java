package com.oleksandrm2dam.minesweeper;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class MinesweeperView extends View {

    private Paint paint;
    private MineField mineField;

    private int tileSize;
    private float halfTileSize;
    private float numberHeight;

    public MinesweeperView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
        mineField = new MineField(9, 9);
    }

    private void init() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    @Override
    public void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        tileSize = w / mineField.getWidth();
        halfTileSize = (float) tileSize / 2;
        numberHeight = (float) h / mineField.getHeight() / 2;
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawTiles(canvas);
    }

    private void drawTiles(Canvas canvas) {
        for(int i = 0; i < mineField.getWidth(); ++i) {
            for(int j = 0; j < mineField.getHeight(); ++j) {
                paint.setStyle(Paint.Style.FILL);
                paint.setColor(Color.GRAY);
                canvas.drawRect(i * tileSize, j * tileSize,
                        i * tileSize + tileSize, j * tileSize + tileSize, paint);

                paint.setStyle(Paint.Style.STROKE);
                paint.setColor(Color.BLACK);
                canvas.drawRect(i * tileSize, j * tileSize,
                        i * tileSize + tileSize, j * tileSize + tileSize, paint);

                paint.setColor(Color.RED);
                paint.setTextSize(numberHeight);
                paint.setStyle(Paint.Style.FILL);
                paint.setTextAlign(Paint.Align.CENTER);
                canvas.drawText(Integer.toString(mineField.getTiles()[i][j].getNumberOfMinesAround()),
                        i * tileSize + halfTileSize, j * tileSize + tileSize, paint);
            }
        }
    }

}
