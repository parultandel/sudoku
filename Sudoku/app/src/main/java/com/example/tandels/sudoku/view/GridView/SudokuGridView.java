package com.example.tandels.sudoku.view.GridView;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.example.tandels.sudoku.util.GameEngine;

/**
 * SudokuGridView : this class is sudoku's grid view with 9 columns
 */

public class SudokuGridView extends GridView {

    private Context context;
    private static final String TAG="SudokuGridView";

    public SudokuGridView(final Context context, AttributeSet attributeSet) {
        super(context,attributeSet);

        this.context=context;
        try {

            SudokuGridViewAdapter gridViewAdapter = new SudokuGridViewAdapter(context);
            setAdapter(gridViewAdapter);
            setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                    int xPos = position % 9;
                    int yPos = position / 9;

                    //  Toast.makeText(context, "XPOS:=" +xPos +"YPOS:=" +yPos , Toast.LENGTH_SHORT).show();

                    GameEngine.getInstance().setSelectedPosition(xPos, yPos);


                }
            });
        }
        catch(Exception e)
        {
            Log.i(TAG,e.getMessage().toString());
        }
    }


}
