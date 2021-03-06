package com.example.tandels.sudoku.util;

import android.content.Context;
import android.media.MediaPlayer;
import android.util.Log;



public class Music {

    private static final String TAG = "SudokuMusic";

    public static MediaPlayer mp=null;
    public static void play(Context context, int resource) {
        try {
            mp = MediaPlayer.create(context, resource);
            mp.setLooping(true);
            mp.start();

            Log.d(TAG, "music started");
        }
        catch (Exception e)
        {
            Log.i(TAG,e.getMessage().toString());
        }

    }

    public static void stop(Context context) {
        try {


            if (mp != null) {
                mp.stop();
                mp.release();
                mp = null;

                Log.d(TAG, "music stopped");
            }
        }
        catch(Exception e)
        {
            Log.i(TAG,e.getMessage().toString());
        }
    }

}
