package com.example.tictactoe;


import androidx.appcompat.app.AppCompatActivity;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    String winner;
    int[] gameState={2,2,2,2,2,2,2,2,2};
    int[][] winningPositions={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    int player=0;
    boolean gameActive=true;
    int c=0;
    @SuppressLint("SetTextI18n")
    public void drop(View view){
        ImageView counter=(ImageView)view;
        int tappedCounter=Integer.parseInt(counter.getTag().toString());
        if(gameActive) {
            if (gameState[tappedCounter] != 2) {
                if (gameState[tappedCounter] == 0)
                    Toast.makeText(this, "This place has already been occupied by 'X'", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(this, "This place has already been occupied by 'O'", Toast.LENGTH_SHORT).show();
            } else {
                c++;
                gameState[tappedCounter] = player;
                if (player == 0) {
                    counter.setImageResource(R.drawable.cross);
                    player = 1;
                } else {
                    counter.setImageResource(R.drawable.circle);
                    player = 0;
                }
                counter.animate().alpha(1).setDuration(500);

                for (int[] winningPosition : winningPositions) {
                    if (gameState[winningPosition[0]] == gameState[winningPosition[1]] && gameState[winningPosition[1]] == gameState[winningPosition[2]] && gameState[winningPosition[0]] != 2) {
                        gameActive = false;
                        if (gameState[winningPosition[0]] == 0) {
                            winner="X";

                        } else {
                            winner="O";

                        }
                        Button playAgain = (Button) findViewById(R.id.button);
                        TextView winnerText = (TextView) findViewById(R.id.winnerTextView);

                        playAgain.setVisibility(View.VISIBLE);
                        winnerText.setText(winner+" has WON the GAME");
                        winnerText.setVisibility(View.VISIBLE);

                    }
                }
                if(c==9 && winner==null){
                    Button playAgain = (Button) findViewById(R.id.button);
                    TextView winnerText = (TextView) findViewById(R.id.winnerTextView);

                    playAgain.setVisibility(View.VISIBLE);
                    winnerText.setText("GAME DRAWN!!");
                    winnerText.setVisibility(View.VISIBLE);
                }

            }
        }
    }

    public void playAgain(View view){
        Button playAgain = findViewById(R.id.button);
        TextView winnerText = findViewById(R.id.winnerTextView);

        playAgain.setVisibility(View.INVISIBLE);
        winnerText.setVisibility(View.INVISIBLE);

        androidx.gridlayout.widget.GridLayout myGridLayout = findViewById(R.id.gridLayout);

        for(int i=0; i<myGridLayout.getChildCount(); i++){
            ((ImageView) myGridLayout.getChildAt(i)).setImageResource(0);
        }

        for(int i=0;i<9;i++){
            gameState[i]=2;
        }

        player=0;
        gameActive=true;
        winner=null;
        c=0;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
