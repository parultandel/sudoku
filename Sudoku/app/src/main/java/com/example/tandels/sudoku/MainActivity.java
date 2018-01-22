package com.example.tandels.sudoku;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import com.example.tandels.sudoku.util.Music;

/**
 * Main Acitivity
 */


public class MainActivity extends Activity implements View.OnClickListener{

    private ImageButton audioButton=null;
    private static final String TAG="MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        // setup click listeners

        View newButton      = findViewById(R.id.new_button);
        View aboutButton    = findViewById(R.id.about_button);
        View exitButton     = findViewById(R.id.exit_button);
         audioButton        = findViewById(R.id.audio_button);


        newButton.setOnClickListener(this);
        aboutButton.setOnClickListener(this);
        exitButton.setOnClickListener(this);
        audioButton.setOnClickListener(this);
        audioButton.setImageResource(R.drawable.audio);
        audioButton.setTag(R.drawable.audio);
    }

    /**
     * handles the click events of the three buttons (About,New Game,Exit)
     * @param view
     */
    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.about_button:
                Intent i = new Intent(this, AboutActivity.class);
                startActivity(i);
                break;

            case R.id.new_button:
                openNewGameDialog();
                break;
            case R.id.exit_button:
                finish();
                break;
            case R.id.audio_button:
                if(Music.mp != null) {
                    audioButton.setImageResource(R.drawable.noaudio);
                    audioButton.setTag(R.drawable.noaudio);
                    Music.stop(this);
                }
                else
                {
                    audioButton.setImageResource(R.drawable.audio);
                    audioButton.setTag(R.drawable.audio);
                    Music.play(this,R.raw.menu);
                }

        }

    }

    /**
     * The dialog box for Game's difficulty level
     */
    private void openNewGameDialog() {
        new AlertDialog.Builder(this)
                .setTitle(R.string.new_game_title)
                .setItems(R.array.difficulty,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialoginterface, int i) {
                                startGame(i);
                            }
                        }
                )
                .show();
    }

    /**
     * Starts the Game Activity with difficulty level
     * @param i
     */
    private void startGame(int i) {
        if (i == -1) {
            Log.d(TAG, "continue our previous game");
        } else {
            Log.d(TAG, "clicked on " + getResources().getStringArray(R.array.difficulty)[i]);
        }
        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtra(GameActivity.KEY_DIFFICULTY, i);
        startActivity(intent);
    }

    /**
     * On Activity resume : play the music if it's not switched off
     */
    @Override
    protected void onResume() {
        super.onResume();
        if((Integer)audioButton.getTag() == R.drawable.audio) {
            Music.play(this, R.raw.menu);
       }
        else
        {
            Music.stop(this);
        }
    }

    /**
     * on Activity stop: stop the music if it's on
     */
    @Override
    protected void onPause() {
        super.onPause();
        Music.stop(this);
    }

}
