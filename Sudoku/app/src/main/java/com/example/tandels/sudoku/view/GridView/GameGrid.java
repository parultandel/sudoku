package com.example.tandels.sudoku.view.GridView;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.widget.Toast;

import com.example.tandels.sudoku.util.SudokuChecker;

/**
 *  GameGrid : set data to the SudokuCellView and get data from the SudokuCellView
 *  @SUdoku :two dimension array of SudokuCellView
 */

public class GameGrid {

    private SudokuCellView[][] Sudoku=new SudokuCellView[9][9];

    private static final String TAG="GameGrid";

    private Context context;

    public GameGrid(Context context)
    {
        this.context=context;
        try {
            for (int row = 0; row < 9; row++) {
                for (int col = 0; col < 9; col++) {
                    Sudoku[row][col] = new SudokuCellView(context);
                }
            }
        }
        catch(Exception e)
        {
            Log.i(TAG,e.getMessage().toString());
        }
    }

    /**
     * set the grid with cellview
     * the initial sudoku cell values has been set to not modifiable flag so user can't delete those values
     * @param grid
     */
    public void setGrid(int [][]grid)
    {
        try {
            for (int row = 0; row < 9; row++) {
                for (int col = 0; col < 9; col++) {
                    Sudoku[row][col].setInitValue(grid[row][col]);
                    if (grid[row][col] != 0) {
                        Sudoku[row][col].setNotModifiable();
                    }
                }
            }
        }
        catch(Exception e)
        {
            Log.i(TAG,e.getMessage().toString());
        }
    }



    public SudokuCellView getGridItem(int x, int y)
    {
        return Sudoku[x][y];
    }

    /**
     * return Sudoku
     * @param position
     * @return
     */
    public SudokuCellView getGridItem(int position)
    {
        try {
            int x = position % 9;
            int y = position / 9;

            return Sudoku[x][y];
        }
        catch(Exception e)
        {
            Log.i(TAG,e.getMessage().toString());
        }
        return null;

    }

    /**
     * set the cell value of the grid
     * @param x
     * @param y
     * @param number
     */
    public void setCellValue(int x,int y,int number)
    {

        Sudoku[x][y].setValue(number);
    }

    /**
     * set the value to the cell with the different color if it is an input by the user
     * @param x
     * @param y
     * @param color
     */
    public void setUsersNumberColor(int x,int y,int color)
    {
        Sudoku[x][y].setUsersNumbersColor(x,y,color);
    }

    /**
     * check the game,whether it has been solved or not
     */
    public void checkGame(){

        try {
            int[][] sudGrid = new int[9][9];
            for (int x = 0; x < 9; x++) {
                for (int y = 0; y < 9; y++) {
                    sudGrid[x][y] = getGridItem(x, y).getValue();
                }
            }

            if (SudokuChecker.getInstance().checkSudoku(sudGrid)) {
                Toast.makeText(context, "You solved the sudoku.", Toast.LENGTH_LONG).show();
            }
        }
        catch(Exception e)
        {
            Log.i(TAG,e.getMessage().toString());
        }

    }



}
