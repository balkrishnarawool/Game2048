package com.balkrishnarawool.android.game2048.model;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by balkrishna on 14/04/2017.
 */

// HighScoreStore class for 2048 game
public class HighScoreStore {

    private static String HIGHEST_SCORE = "HighScoreStore.HIGHEST_SCORE";

    public static int getHighestScore(Activity activity) {
        SharedPreferences sharedPref = activity.getPreferences(Context.MODE_PRIVATE);
        int highScore = sharedPref.getInt(HIGHEST_SCORE, 0);
        return highScore;
    }

    public static void updateHighestScore(Activity activity, int score) {
        int prevScore = getHighestScore(activity);
        if(score > prevScore) {
            SharedPreferences sharedPref = activity.getPreferences(Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putInt(HIGHEST_SCORE, score);
            editor.commit();
        }
    }

}
