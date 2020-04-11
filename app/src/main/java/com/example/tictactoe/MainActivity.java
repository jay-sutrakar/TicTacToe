package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    int activePlayer=0;
    int[] gameState={2,2,2,2,2,2,2,2,2};
    int[][] winPositions={{0,1,2},{3,4,5},{6,7,8},
                         {0,3,6},{1,4,7},{2,5,8},
                         {0,4,8},{2,4,6} };
    boolean gameActive=true;
    int tapCount=0;
    public void playerTab(View view){
        tapCount++;
        ImageView img=(ImageView) view;
        int tappedImage=Integer.parseInt(img.getTag().toString());
        TextView status=findViewById(R.id.status);
        if(!gameActive){
            gameReset(view);

        }
        else if(gameState[tappedImage]==2) {
            gameState[tappedImage]=activePlayer;
            img.setTranslationY(-1000f);
            if (activePlayer == 0) {

                img.setImageResource(R.drawable.x);
                activePlayer = 1;
                status=findViewById(R.id.status);
                status.setText("O's turn");
            } else {
                img.setImageResource(R.drawable.o);
                status=findViewById(R.id.status);
                status.setText("X's turn");
                activePlayer = 0;
            }
            img.animate().translationYBy(1000f).setDuration(300);
        }
        // check if any player has won
        for(int[] winPosition: winPositions){
            if(gameState[winPosition[0]]==gameState[winPosition[1]] && gameState[winPosition[1]]==gameState[winPosition[2]] &&
            gameState[winPosition[0]]!=2){
                String winnerStr="";
                gameActive=false;
                if(gameState[winPosition[0]]==0){
                    winnerStr="X has won";
                }else{
                    winnerStr="O has won";
                }
                status=findViewById(R.id.status);
                status.setText(winnerStr);
            }
        }
        if(tapCount==9 && gameActive){
            status.setText("Match Draw");
            gameActive=false;
        }
    }
    public void gameReset(View view){
        tapCount=0;
        gameActive=true;
        activePlayer=0;
        for(int i=0;i<gameState.length;i++){
            gameState[i]=2;
        }
        ((ImageView)findViewById(R.id.imageView1)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView2)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView4)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView7)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView8)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView9)).setImageResource(0);
        TextView status=findViewById(R.id.status);
        status.setText("X's turn tap to play");

    }
}
