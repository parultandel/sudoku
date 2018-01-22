package com.example.tandels.sudoku.view.ButtonsGridView;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;

import com.example.tandels.sudoku.R;

/**
 * This GridView for input numbers 1-9 and delete the entered number to the Game Grid
 */

public class ButtonsGridView extends GridView{

    public static final String TAG="ButtonGridView";

    public ButtonsGridView( Context context , AttributeSet attrs ){
        super(context , attrs);

        ButtonsGridViewAdapter gridViewAdapter = new ButtonsGridViewAdapter(context);

        setAdapter(gridViewAdapter);
    }

    /**
     * inner adapter class for the GridView
     */
    class ButtonsGridViewAdapter extends BaseAdapter {

        private Context context;

        public ButtonsGridViewAdapter(Context context) {
            this.context = context;
        }

        @Override
        public int getCount() {
            return 10;                                                          //return 10 buttons count: 1-9 and delete button
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {                                   //return the position of selected button from the buttons grid
            return position;
        }

        /**
         * Setup a view of the buttons grid
         * @param position
         * @param convertView
         * @param parent
         * @return
         */
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View v = convertView;


            try {
                if (v == null) {
                    LayoutInflater inflater = ((Activity) context).getLayoutInflater();
                    v = inflater.inflate(R.layout.button, parent, false);

                    NumbersButton btn;
                    btn = (NumbersButton) v;                                         //creates the view of AppcompactButtons to the ButtonsGridView
                    btn.setTextSize(18);
                    btn.setId(position);


                    if (position != 9) {                                            //buttons' indices are 0-9 so their text on the button should be index+1,here index is their position in gridview
                        btn.setText(String.valueOf(position + 1));
                        btn.setNumber(position + 1);
                    } else {
                        btn.setText("DEL");
                        btn.setNumber(0);                                           //Delete button's number is set to 0 because on grid 0 value means blank cell.
                    }
                    return btn;
                }
            }
            catch(Exception e)
            {
                Log.i(TAG,e.getMessage().toString());
            }

            return v;
        }

    }
}
