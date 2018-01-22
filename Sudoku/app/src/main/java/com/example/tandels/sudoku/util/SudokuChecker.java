package com.example.tandels.sudoku.util;

import android.util.Log;

/**
 * SudokuChecker: Singleton class to check the conflicts while user enters the numbers in Sudoku Grid
 */

public class SudokuChecker {
    private static SudokuChecker instance;
    private static final String TAG = "SudokuChecker";

    private SudokuChecker(){}

    public static SudokuChecker getInstance(){
        if( instance == null ){
            instance = new SudokuChecker();
        }
        return instance;
    }

    public boolean checkSudoku( int[][] Sudoku){
        return (checkHorizontal(Sudoku) && checkVertical(Sudoku) && checkRegions(Sudoku));
    }

    /**
     * check the entire row for the conflicts
     * @param Sudoku
     * @return          :if there is a conflicts return false else true
     */
    private boolean checkHorizontal(int[][] Sudoku) {
        boolean result=true;

        try {
            for (int y = 0; y < 9; y++) {
                for (int xPos = 0; xPos < 9; xPos++) {

                    if (Sudoku[xPos][y] == 0) {
                        result= false;
                        return result;
                    }
                    for (int x = xPos + 1; x < 9; x++) {
                        if (Sudoku[xPos][y] == Sudoku[x][y] || Sudoku[x][y] == 0) {
                            result= false;
                            return result;
                        }
                    }
                }
            }
        }
        catch(Exception e)
        {
            Log.i(TAG,e.getMessage().toString());
        }
        return result;
    }

    /**
     * check the entire row for the conflicts
     * @param Sudoku
     * @return          :if there is a conflicts return false else true
     */
    private boolean checkVertical(int[][] Sudoku) {
        boolean result=true;
        try {
            for (int x = 0; x < 9; x++) {
                for (int yPos = 0; yPos < 9; yPos++) {

                    if (Sudoku[x][yPos] == 0) {
                        result= false;
                        return result;
                    }
                    for (int y = yPos + 1; y < 9; y++) {
                        if (Sudoku[x][yPos] == Sudoku[x][y] || Sudoku[x][y] == 0) {
                            result= false;
                            return result;
                        }
                    }
                }
            }
        }
        catch(Exception e)
        {
            Log.i(TAG,e.getMessage().toString());
        }
        return result;
    }

    /**
     * check the conflicts in all the nine regions
     * @param Sudoku
     * @return          :if there is a conflicts return false else true
     */
    private boolean checkRegions(int[][] Sudoku) {
        boolean result=true;
        try {
            for (int xRegion = 0; xRegion < 3; xRegion++) {
                for (int yRegion = 0; yRegion < 3; yRegion++) {
                    if (!checkRegion(Sudoku, xRegion, yRegion)) {
                        result= false;
                        return result;
                    }
                }
            }
        }
        catch(Exception e)
        {
            Log.i(TAG,e.getMessage().toString());
        }
        return result;
    }

    /**
     * check the conflicts in the current region
     * @param Sudoku
     * @param xRegion
     * @param yRegion
     * @return          :if there is a conflicts return false else true
     */
    private boolean checkRegion(int[][] Sudoku , int xRegion , int yRegion){
        boolean result=true;
        try {
            for (int xPos = xRegion * 3; xPos < xRegion * 3 + 3; xPos++) {
                for (int yPos = yRegion * 3; yPos < yRegion * 3 + 3; yPos++) {
                    for (int x = xPos; x < xRegion * 3 + 3; x++) {
                        for (int y = yPos; y < yRegion * 3 + 3; y++) {
                            if (((x != xPos || y != yPos) && Sudoku[xPos][yPos] == Sudoku[x][y]) || Sudoku[x][y] == 0) {
                                result= false;
                                return result;
                            }
                        }
                    }
                }
            }
        }
        catch(Exception e)
        {
            Log.i(TAG,e.getMessage().toString());
        }
        return result;
    }
}
