package com.example.tandels.sudoku.view.GridView;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;

import com.example.tandels.sudoku.R;

/**
 * BaseSudokuCellView : it's a base class for CellView which is for the parameters declaration and getter-setter methods for the CellView
 */

public class BaseSudokuCellView extends View {

    private int value;                                       //number of the cell
    private boolean modifiable=true;
    int cellPosX;                                           //selected cell's row position
    int cellPosY;                                           //selected cell's column position
    int color;                                              //color for the user input number
    private static final String TAG = "BaseSudokuCellView";




    public BaseSudokuCellView(Context context) {
        super(context);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }

    public void setNotModifiable()
    {
        modifiable=false;
    }

    /**
     * set the initial sudoku puzzle value to the cell
     * @param value
     */
    public void setInitValue(int value)
    {
        this.value=value;
        invalidate();
    }

    public void setValue(int value)
    {
        if(modifiable)
        {
            this.value=value;
        }
        invalidate();
    }

    /**
     * it's set the paint color for the user's input number and also set the row and column position of the selected cell
     * @param xPos
     * @param yPos
     * @param color
     */
    public void setUsersNumbersColor(int xPos,int yPos,int color)
    {
        try {
            cellPosX = xPos;
            cellPosY = yPos;
            this.color = color;
        }
        catch(Exception e)
        {
            Log.i(TAG,e.getMessage().toString());
        }
    }

    public int getValue() {
        return value;
    }

    public void setCoord(int position){
        try {
            cellPosX = position % 9;                                //set the row value:for ex,position=35 => cellPosx=35 % 9 which is 8
            cellPosY = position / 9;                                //set the column value: for ex,position=35 => cellPosY=35 / 9 which is 3
        }
        catch(Exception e)
        {
            Log.i(TAG,e.getMessage().toString());
        }
    }



}
