package com.oleksandrm2dam.minesweeper;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

public class CanvasView extends View {

    public int width, height;
    private Bitmap bitmap;
    private Canvas canvas;
    private Paint paint;
    private MineField mineField;

    public CanvasView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mineField = new MineField(9, 9);
        paint = new Paint();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        canvas = new Canvas(bitmap);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawMineField();
    }

    private void drawMineField() {
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(10);
        canvas.drawRect(100, 100, 200, 200, paint);
        System.out.println("Dibujando!!!");
    }

}
