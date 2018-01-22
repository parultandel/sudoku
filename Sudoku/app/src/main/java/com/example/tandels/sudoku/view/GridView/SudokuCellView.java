package com.example.tandels.sudoku.view.GridView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;

import com.example.tandels.sudoku.R;

public class SudokuCellView extends BaseSudokuCellView {

    private Paint mPaint;
    private static final String TAG="SUdokuCellView";

    public SudokuCellView(Context context ){
        super(context);

        mPaint = new Paint();

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        drawPuzzlesNumber(canvas);
        drawRect(canvas);
        if(modifiable == true)
            drawUsersNumber(canvas,color);

        try {

            if (cellPosX % 3 == 0 && cellPosX != 0)
                drawVerticalDarkLines(canvas);
            if (cellPosY % 3 == 0 && cellPosY != 0)
                drawHorizontalDarkLines(canvas);
        }
        catch(Exception e)
        {
            Log.i(TAG,e.getMessage().toString());
        }
    }

    /**
     * draw the numbers entered by the user on the cell of Sudoku grid
     * @param canvas
     * @param color
     */
    private void drawUsersNumber(Canvas canvas,int color)
    {
        try {
            mPaint.setColor(color);
            mPaint.setTextSize(80);
            mPaint.setStyle(Paint.Style.FILL);

            Rect bounds = new Rect();
            mPaint.getTextBounds(String.valueOf(getValue()), 0, String.valueOf(getValue()).length(), bounds);

            if (getValue() != 0) {
                canvas.drawText(String.valueOf(getValue()), (getWidth() - bounds.width()) / 2, (getHeight() + bounds.height()) / 2, mPaint);
            }
        }
        catch(Exception e)
        {
            Log.i(TAG,e.getMessage().toString());
        }
    }

    /**
     * draw the puzzle numbers
     * @param canvas
     */
    private void drawPuzzlesNumber(Canvas canvas){
        try {
            mPaint.setColor(Color.BLACK);
            mPaint.setTextSize(80);
            mPaint.setStyle(Paint.Style.FILL);

            Rect bounds = new Rect();
            mPaint.getTextBounds(String.valueOf(getValue()), 0, String.valueOf(getValue()).length(), bounds);

            if (getValue() != 0) {
                canvas.drawText(String.valueOf(getValue()), (getWidth() - bounds.width()) / 2, (getHeight() + bounds.height()) / 2, mPaint);
            }
        }
        catch(Exception e)
        {
            Log.i(TAG,e.getMessage().toString());
        }
    }


    /**
     * draw the rectangle for each cell
     * @param canvas
     */
    private void drawRect(Canvas canvas) {
        try {
            mPaint.setColor(getResources().getColor(R.color.puzzle_dark));
            mPaint.setStrokeWidth(3);
            mPaint.setStyle(Paint.Style.STROKE);


            canvas.drawRect(0, 0, getWidth(), getHeight(), mPaint);
        }
        catch(Exception e)
        {
            Log.i(TAG,e.getMessage().toString());
        }
    }

    /**
     * draw the vertical dark lines to separate out the regions
     * @param canvas
     */
    private void drawVerticalDarkLines(Canvas canvas) {
        try {

            mPaint.setColor(Color.BLACK);
            mPaint.setStrokeWidth(9);
            mPaint.setStyle(Paint.Style.STROKE);

            canvas.drawLine(cellPosX, 0, cellPosX, getHeight(), mPaint);
        }
        catch(Exception e)
        {
            Log.i(TAG,e.getMessage().toString());
        }
    }

    /**
     * draw the horizontal dark lines to separate out the regions
     * @param canvas
     */
    private void drawHorizontalDarkLines(Canvas canvas) {
        try {
            mPaint.setColor(Color.BLACK);
            mPaint.setStrokeWidth(9);
            mPaint.setStyle(Paint.Style.STROKE);

            canvas.drawLine(0, cellPosY, getWidth(), cellPosY, mPaint);
        }
        catch(Exception e)
        {
            Log.i(TAG,e.getMessage().toString());
        }
    }
}