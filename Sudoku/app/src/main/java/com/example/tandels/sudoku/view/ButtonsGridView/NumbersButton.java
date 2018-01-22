package com.example.tandels.sudoku.view.ButtonsGridView;

import android.content.Context;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;

import com.example.tandels.sudoku.util.GameEngine;

/**
 * NumbersButton is a Appcompact button of the ButtonsGridView
 */

public class NumbersButton extends AppCompatButton implements OnClickListener{

    private int number;

    public NumbersButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        GameEngine.getInstance().setNumber(number);                                 //
    }

    public void setNumber(int number){
        this.number = number;
    }
}
