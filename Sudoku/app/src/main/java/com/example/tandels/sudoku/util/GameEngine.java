package com.example.tandels.sudoku.util;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;

import com.example.tandels.sudoku.R;
import com.example.tandels.sudoku.view.GridView.GameGrid;

/**
 * GameEnfine : Singleton class which gets the Sudoku matrix from the sudokuGenerator and assign it to the Game grid
 */

public class GameEngine {

    private static GameEngine instance;

    private GameGrid grid=null;

    private int selectedPosX=-1,selectedPosY=-1;
    public static final String TAG="GameEngine";




    private GameEngine()
    {


    }
    public static GameEngine getInstance()
    {
        if(instance==null)
        {
            instance=new GameEngine();
        }
        return instance;
    }

    /**
     * Assign sudoku matrix to the Game Grid
     * @param context
     * @param difficultyLevel
     */
    public void createGrid(Context context,int difficultyLevel)
    {
        try {
            int Sudoku[][] = SudokuGenerator.getInstance().generateGrid(difficultyLevel);
            grid = new GameGrid(context);
            grid.setGrid(Sudoku);
        }
        catch(Exception e)
        {
            Log.i(TAG,e.getMessage().toString());
        }
    }

    public GameGrid getGrid()
    {
        return grid;
    }

    /**
     *
     * @param x
     * @param y
     */
    public void setSelectedPosition(int x,int y)
    {
        try {
            selectedPosX = x;
            selectedPosY = y;
        }
        catch(Exception e)
        {
            Log.i(TAG,e.getMessage().toString());
        }
    }

    /**
     * set number to the selected position of the Game Grid
     * @param number
     */
    public void setNumber(int number)
    {
        try {
            if (selectedPosX != -1 && selectedPosY != -1) {
                grid.setCellValue(selectedPosX, selectedPosY, number);
                if (number != 0)
                    grid.setUsersNumberColor(selectedPosX, selectedPosY, Color.BLUE);

            }
            grid.checkGame();
        }
        catch(Exception e)
        {
            Log.i(TAG,e.getMessage().toString());
        }
    }
}
