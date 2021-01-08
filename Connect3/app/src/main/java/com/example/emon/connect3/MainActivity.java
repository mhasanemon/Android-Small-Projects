package com.example.emon.connect3;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //0 = yellow
    //1 = red

    int activePlayer = 0;
    boolean gameIsActive = true;

    //-1 means unplayed
    int[] gameState = {-1,-1,-1,-1,-1,-1,-1,-1,-1};
    int[][] winningPosition = {{0,1,2},{3,4,5},{6,7,8},{0,4,8},{1,4,7},{2,4,8},{2,4,6}};

    public void dropIn(View view){
        ImageView counter = (ImageView) view;
        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if(gameState[tappedCounter]==-1&& gameIsActive){
            gameState[tappedCounter]= activePlayer;

            counter.setTranslationY(-1000f);

            if(activePlayer==0){
                counter.setImageResource(R.drawable.yellow);
                activePlayer = 1;
            }
            else {
                counter.setImageResource(R.drawable.red);
                activePlayer = 0 ;
            }


            counter.animate().translationYBy(1000f).setDuration(200);

            for(int[]winnigPosition:winningPosition){
                if(gameState[winnigPosition[0]]==gameState[winnigPosition[1]]&&gameState[winnigPosition[1]]==gameState[winnigPosition[2]]&& gameState[winnigPosition[0]]!=-1){
                    gameIsActive = false;
                    Toast.makeText(getApplicationContext(),"Congrats!",Toast.LENGTH_SHORT).show();
                    String winner = "Red";

                    if(gameState[winnigPosition[0]]==0){
                        winner = "Yellow";
                    }



                    TextView textview = (TextView) findViewById(R.id.editText);
                    textview.setText(winner+" Has Won!");

                    LinearLayout layout = (LinearLayout) findViewById(R.id.PlayAgainLayout);
                    layout.setVisibility(View.VISIBLE);

                }else {
                    boolean gameIsOver = true;
                    for (int counterState : gameState) {

                        if (counterState == -1) gameIsOver = false;

                    }

                    if (gameIsOver) {

                        TextView winnerMessage = (TextView) findViewById(R.id.editText);

                        winnerMessage.setText("It's a draw");

                        LinearLayout layout = (LinearLayout)findViewById(R.id.PlayAgainLayout);

                        layout.setVisibility(View.VISIBLE);

                    }
                }
            }
        }

    }

    public void playAgain(View view) {

        gameIsActive = true;

        LinearLayout layout = (LinearLayout)findViewById(R.id.PlayAgainLayout);

        layout.setVisibility(View.INVISIBLE);

        activePlayer = 0;

        for (int i = 0; i < gameState.length; i++) {

            gameState[i] = -1;

        }

        GridLayout gridLayout = (GridLayout)findViewById(R.id.gridLayout);

        for (int i = 0; i< gridLayout.getChildCount(); i++) {

            ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);

        }

    }

    public void close(View view){
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
