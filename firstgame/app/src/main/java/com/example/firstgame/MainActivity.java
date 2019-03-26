package com.example.firstgame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    //setting players to allocate colors
    //0 :yellow 1:red 2:empty
    int activePlayer = 0;

    boolean gameActive = true;

    int[] gamesState ={2,2,2,2,2,2,2,2,2};

    //winning possibilities
    int[][] winningPositions ={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};

    public void dropIn(View view)
    {
        //identify which image was tap on
        ImageView counter = (ImageView) view;

        Log.i("value",counter.getTag().toString());

        //getting the value from getTag and converts it into a integer
        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if(gamesState[tappedCounter]== 2 && gameActive) {
            //update value with active player
            gamesState[tappedCounter] = activePlayer;

            //take out from top the screen | image comes from top of the screen
            counter.setTranslationY(-1500);


            if (activePlayer == 0) {
                //set the yellow image and set the player as 1
                counter.setImageResource(R.drawable.yellow);

                activePlayer = 1;
            } else {
                //set the red image and set the player as 0
                counter.setImageResource(R.drawable.red);

                activePlayer = 0;
            }

            // set image resource


            counter.animate().translationYBy(1500).rotation(1800).setDuration(1000);

            for (int[] winningPosition : winningPositions) {

                //check the winning possibilities
                if (gamesState[winningPosition[0]] == gamesState[winningPosition[1]] && gamesState[winningPosition[1]] == gamesState[winningPosition[2]] && gamesState[winningPosition[0]] != 2) {

                    gameActive =false;
                    String winner = "";
                    if (activePlayer == 1) {


                        winner = "Yellow";

                    } else {

                        winner = "Red";
                    }



                    Button playAgainButton = (Button) findViewById(R.id.playAgainButton);

                    TextView winnerTextView = (TextView) findViewById(R.id.winnerTextView);

                    winnerTextView.setText(winner + " has won !");

                    playAgainButton.setVisibility(View.VISIBLE);

                    winnerTextView.setVisibility(View.VISIBLE);
                }

            }
        }


    }


    public void playAgain(View view){

        Button playAgainButton = (Button) findViewById(R.id.playAgainButton);

        TextView winnerTextView = (TextView) findViewById(R.id.winnerTextView);

        playAgainButton.setVisibility(View.INVISIBLE);

        winnerTextView.setVisibility(View.INVISIBLE);

        GridLayout gridLayout = (GridLayout)findViewById(R.id.gridLayout);

        for(int i = 0; i<gridLayout.getChildCount();i++){

                ImageView child = (ImageView) gridLayout.getChildAt(i);

                child.setImageDrawable(null);



        }

            for(int i=0;i<gamesState.length;i++) {

                gamesState[i] = 2;
            }
                activePlayer = 0;
                gameActive = true;


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
