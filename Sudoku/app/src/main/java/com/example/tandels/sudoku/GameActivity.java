package com.example.tandels.sudoku;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Chronometer;
import android.widget.ImageButton;
import android.app.AlertDialog;
import android.widget.TextView;


import com.example.tandels.sudoku.util.GameEngine;

import com.example.tandels.sudoku.util.Music;

import java.util.Timer;

/**
 * GameActivity : Play Sudoku Game
 */

public class GameActivity extends Activity implements View.OnClickListener{

    public static final String KEY_DIFFICULTY = "difficulty_level";
    public static final int DIFFICULTY_EASY   = 0;
    public static final int DIFFICULTY_MEDIUM = 1;
    public static final int DIFFICULTY_HARD   = 2;

    private static final String TAG="GameActivity";



    private Chronometer gameTimer;
    private ImageButton audioButton=null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);


        gameTimer          =(Chronometer)findViewById(R.id.gametimer);                      //Chronometer view for game timer
        audioButton        = (ImageButton) findViewById(R.id.audio_button);                 //ImageButton for audio

        int difficultyLevel = getIntent().getIntExtra(KEY_DIFFICULTY,
                DIFFICULTY_EASY);


        GameEngine.getInstance().createGrid(this,difficultyLevel);                  //get the Sudoku with the selected difficulty level

        audioButton.setOnClickListener(this);
        audioButton.setImageResource(R.drawable.audio);
        audioButton.setTag(R.drawable.audio);
    }

    /**
     * play the music on and off on click event of audio ImageButton,change the TAG of ImageButton accordingly
     * @param view
     */
    @Override
    public void onClick(View view) {
        try {
            if (Music.mp != null) {
                audioButton.setImageResource(R.drawable.noaudio);
                audioButton.setTag(R.drawable.noaudio);
                Music.stop(this);
            } else {
                audioButton.setImageResource(R.drawable.audio);
                audioButton.setTag(R.drawable.audio);
                Music.play(this, R.raw.menu);
            }
        }catch(Exception e)
        {
            Log.i(TAG,e.getMessage().toString());
        }

    }

    /**
     * on activity resume : timer and music will start
     */
    @Override
    protected void onResume() {
        super.onResume();
        try {
            if ((Integer) audioButton.getTag() == R.drawable.audio) {
                Music.play(this, R.raw.gameboard);
            } else {
                Music.stop(this);
            }
            gameTimer.start();
        }
        catch(Exception e)
        {
            Log.i(TAG,e.getMessage().toString());
        }
    }


    /**
     * on activity pause : timer and music will stop
     */
    @Override
    protected void onPause() {
        super.onPause();
        try {
            Music.stop(this);
            gameTimer.stop();
        }
        catch(Exception e)
        {
            Log.i(TAG,e.getMessage().toString());
        }
    }



}
