package com.example.tandels.sudoku.util;

import android.util.Log;

import java.util.ArrayList;
import java.util.Random;



/**
 * SudokuGenerator: Singleton class which generates the sudoku 9X9 matrix
 * generateGrid():  creates sudoku's 81 elements with numbers and blanks as per the difficulty level
 * (easy:Removes 10 elements, medium: Removes 30 elements, hard: Removes 46 elements)

 */



public class SudokuGenerator {
    private static SudokuGenerator instance;
    private static final String TAG = "SudokuGenerator";

    private ArrayList<ArrayList<Integer>> Available = new ArrayList<ArrayList<Integer>>();

    private Random rand = new Random();

    private SudokuGenerator(){}

    //create only one instance

    public static SudokuGenerator getInstance(){
        if( instance == null ){
            instance = new SudokuGenerator();
        }
        return instance;
    }

    public int[][] generateGrid(int difficultyLevel){
        int[][] Sudoku = new int[9][9];

        int currentPos = 0;

        try {
            while (currentPos < 81) {
                if (currentPos == 0) {
                    clearGrid(Sudoku);
                }

                if (Available.get(currentPos).size() != 0) {
                    int index = rand.nextInt(Available.get(currentPos).size());     //generate random index between 0-9
                    int number = Available.get(currentPos).get(index);              //get the number from the Available arraylist and assign it to Sudoku matrix

                    if (!checkConflict(Sudoku, currentPos, number)) {   //check the same number is not already assign it to the current position
                        int xPos = currentPos % 9;              //xPos gives you row number of the current cell position
                        int yPos = currentPos / 9;              //yPos gives you column number of the current position

                        Sudoku[xPos][yPos] = number;

                        Available.get(currentPos).remove(index);

                        currentPos++;
                    } else {
                        Available.get(currentPos).remove(index);
                    }

                } else {
                    for (int i = 1; i <= 9; i++) {
                        Available.get(currentPos).add(i);
                    }
                    currentPos--;
                }
            }


            Sudoku = removeElements(Sudoku, difficultyLevel);
        }
        catch(Exception e)
        {
            Log.i(TAG,e.getMessage().toString());
        }
        return Sudoku;
    }



    /**
     * remove elements from the Sudoku matrix using with random row and column values
     * removed element's value will 0
     * @param Sudoku
     * @param difficultyLevel
     * @return
     */
    public int[][] removeElements( int[][] Sudoku,int difficultyLevel ){
        int i = 0;

        int removeNoOfElements=0;

        try {
            if (difficultyLevel == 0) {
                removeNoOfElements = 10;
            } else if (difficultyLevel == 1) {
                removeNoOfElements = 30;
            } else {
                removeNoOfElements = 46;
            }

            while (i < removeNoOfElements) {
                int x = rand.nextInt(9);    //random row value between 0-9
                int y = rand.nextInt(9);    //random column value between 0-9

                if (Sudoku[x][y] != 0) {
                    Sudoku[x][y] = 0;
                    i++;
                }
            }
        }
        catch(Exception e)
        {
            Log.i(TAG,e.getMessage().toString());
        }
        return Sudoku;

    }



    /**
     * Clears the matrix before inserting numbers to Sudoku matrix and insert the numbers to Available arraylist
     * @param Sudoku
     */

    private void clearGrid(int [][] Sudoku){
        try {


            Available.clear();

            for (int y = 0; y < 9; y++) {
                for (int x = 0; x < 9; x++) {
                    Sudoku[x][y] = -1;
                }
            }

            for (int x = 0; x < 81; x++) {
                Available.add(new ArrayList<Integer>());
                for (int i = 1; i <= 9; i++) {
                    Available.get(x).add(i);
                }
            }
        }
        catch(Exception e)
        {
            Log.i(TAG,e.getMessage().toString());
        }
    }

    /**
     *
     * @param Sudoku
     * @param currentPos
     * @param number
     * @return
     */
    private boolean checkConflict( int[][] Sudoku , int currentPos , final int number){
        boolean result=false;
        try {
            int xPos = currentPos % 9;
            int yPos = currentPos / 9;


            if (checkHorizontalConflict(Sudoku, xPos, yPos, number) || checkVerticalConflict(Sudoku, xPos, yPos, number) || checkRegionConflict(Sudoku, xPos, yPos, number)) {
                result = true;
                return result;
            }
        }
        catch(Exception e)
        {
           Log.i(TAG,e.getMessage().toString());
        }

        return result;
    }

    /**
     * compare the number in the entire row
     * @param Sudoku
     * @param xPos
     * @param yPos
     * @param number
     * @return      :if there is conflicts return true else false
     */
    private boolean checkHorizontalConflict( final int[][] Sudoku , final int xPos , final int yPos , final int number ){
        boolean result=false;

        try {
            for (int x = xPos - 1; x >= 0; x--) {
                if (number == Sudoku[x][yPos]) {
                    result = true;
                    return result;
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
     * compare the number in the entire column
     * @param Sudoku
     * @param xPos
     * @param yPos
     * @param number
     * @return
     */
    private boolean checkVerticalConflict( final int[][] Sudoku , final int xPos , final int yPos , final int number ){
        boolean result=false;
        try {
            for (int y = yPos - 1; y >= 0; y--) {
                if (number == Sudoku[xPos][y]) {
                    result= true;
                    return result;
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
     * compare the number in the entire region
     * @param Sudoku
     * @param xPos      :row number
     * @param yPos      :column number
     * @param number
     * @return          :if there is conflicts return true else false
     */
    private boolean checkRegionConflict( final int[][] Sudoku , final int xPos , final int yPos , final int number ){
        boolean result=false;

        try {
            int xRegion = xPos / 3;                                     //gives xRegion between 0-2
            int yRegion = yPos / 3;                                     //gives yRegion between 0-2

            for (int x = xRegion * 3; x < xRegion * 3 + 3; x++) {
                for (int y = yRegion * 3; y < yRegion * 3 + 3; y++) {
                    if ((x != xPos || y != yPos) && number == Sudoku[x][y]) {     //number should not be the same in any of the nine numbers of the region and doesn't compare with the same number
                        result = true;
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
}