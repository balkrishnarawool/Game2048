package com.balkrishnarawool.android.game2048;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.balkrishnarawool.android.game2048.model.Engine;
import com.balkrishnarawool.android.game2048.model.HighScoreStore;

public class MainActivity extends AppCompatActivity {

    private TextView score;
    private TextView highestScore;
    private TextView scoreLabel;
    private TextView highestScoreLabel;
    private GridLayout board;
    private TextView[][] cellTVs;

    private Engine engine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        board = (GridLayout) findViewById(R.id.board);
        scoreLabel = (TextView) findViewById(R.id.score_label);
        scoreLabel.setTypeface(CustomFont.getTypeFace(this));
        score = (TextView) findViewById(R.id.score);
        score.setTypeface(CustomFont.getTypeFace(this));
        highestScoreLabel = (TextView) findViewById(R.id.high_score_label);
        highestScoreLabel.setTypeface(CustomFont.getTypeFace(this));
        highestScore = (TextView) findViewById(R.id.high_score);
        highestScore.setTypeface(CustomFont.getTypeFace(this));

        int[][] cellIDs = {{R.id.cell_0_0, R.id.cell_0_1, R.id.cell_0_2, R.id.cell_0_3},
                {R.id.cell_1_0, R.id.cell_1_1, R.id.cell_1_2, R.id.cell_1_3},
                {R.id.cell_2_0, R.id.cell_2_1, R.id.cell_2_2, R.id.cell_2_3},
                {R.id.cell_3_0, R.id.cell_3_1, R.id.cell_3_2, R.id.cell_3_3}};

        cellTVs = new TextView[4][4];
        for (int i = 0; i < cellIDs.length; i++) {
            for (int j = 0; j < cellIDs[i].length; j++) {
                cellTVs[i][j] = (TextView) findViewById(cellIDs[i][j]);
                cellTVs[i][j].setTypeface(CustomFont.getTypeFace(this));
             }
        }

        setTouchListener();

        engine = new Engine();
        engine.putNumberRandomly();
        syncUI();
    }

    private void syncUI() {
        int cells[][] = engine.getCells();
        for (int i = 0; i < cellTVs.length; i++) {
            for (int j = 0; j < cellTVs[i].length; j++) {
                 cellTVs[i][j].setText(""+(cells[i][j]==0?"":cells[i][j]));
                 setBackground(cellTVs[i][j], cells[i][j]);
            }
        }

        score.setText(""+engine.getScore());
        HighScoreStore.updateHighestScore(this, engine.getScore());
        highestScore.setText(""+HighScoreStore.getHighestScore(this));
    }

    private void setBackground(TextView cell, int value) {
        switch(value) {
            case 0: cell.setBackgroundResource(R.drawable.cell_0); break;
            case 2: cell.setBackgroundResource(R.drawable.cell_2); cell.setTextColor(Color.DKGRAY); break;
            case 4: cell.setBackgroundResource(R.drawable.cell_4); cell.setTextColor(Color.DKGRAY); break;
            case 8: cell.setBackgroundResource(R.drawable.cell_8); cell.setTextColor(Color.WHITE); break;
            case 16: cell.setBackgroundResource(R.drawable.cell_16); cell.setTextColor(Color.WHITE); break;
            case 32: cell.setBackgroundResource(R.drawable.cell_32); cell.setTextColor(Color.WHITE); break;
            case 64: cell.setBackgroundResource(R.drawable.cell_64); cell.setTextColor(Color.WHITE); break;
            case 128: cell.setBackgroundResource(R.drawable.cell_128); cell.setTextColor(Color.WHITE); break;
            case 256: cell.setBackgroundResource(R.drawable.cell_256); cell.setTextColor(Color.WHITE); break;
            case 512: cell.setBackgroundResource(R.drawable.cell_512); cell.setTextColor(Color.WHITE); break;
            case 1024: cell.setBackgroundResource(R.drawable.cell_1024); cell.setTextColor(Color.WHITE); break;
            case 2048: cell.setBackgroundResource(R.drawable.cell_2048); cell.setTextColor(Color.WHITE); break;
            default: break;
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private void setTouchListener() {
        board.setOnTouchListener(new OnSwipeTouchListener(this){
            public void onSwipeRight() {
                int count = engine.moveCellsRight();
                if(count == 0)
                    checkIfGameOver();
                else {
                    engine.putNumberRandomly();
                    syncUI();
                }
            }
            public void onSwipeLeft() {
                int count = engine.moveCellsLeft();
                if(count == 0)
                    checkIfGameOver();
                else {
                    engine.putNumberRandomly();
                    syncUI();
                }
            }
            public void onSwipeTop() {
                int count = engine.moveCellsUp();
                if(count == 0)
                    checkIfGameOver();
                else {
                    engine.putNumberRandomly();
                    syncUI();
                }
            }
            public void onSwipeBottom() {
                int count = engine.moveCellsDown();
                if(count == 0)
                    checkIfGameOver();
                else {
                    engine.putNumberRandomly();
                    syncUI();
                }
            }
        });
    }

    private void checkIfGameOver() {
        if(engine.isGameOver())
            onGameOver();
    }

    private void onGameOver() {
        new AlertDialog.Builder(this)
                .setTitle("Game Over")
                .setMessage("Game over! Want to play more?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        restart();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_info)
                .show();
    }

    private void restart() {
        engine = new Engine();
        engine.putNumberRandomly();
        syncUI();
    }
}
