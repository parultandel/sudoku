package com.example.tandels.sudoku.view.GridView;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.tandels.sudoku.R;
import com.example.tandels.sudoku.util.GameEngine;

/**
 * SudokuGridViewAdapter : adapter class for SudokuGridView
 */

public class SudokuGridViewAdapter extends BaseAdapter {

    private Context context;

    public SudokuGridViewAdapter(Context context)
    {
        this.context=context;
    }


    @Override
    public int getCount() {
        return 81;                                                              //81 cells in sudoku grid
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position ,View convertView, ViewGroup parent) {


        GameEngine.getInstance().getGrid().getGridItem(position).setCoord(position);

        return GameEngine.getInstance().getGrid().getGridItem(position);
    }
}
