package com.example.brandongomez.cs121asg1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.graphics.Color;
import android.widget.ImageView;
import android.content.Intent;

public class MainActivity extends AppCompatActivity {
    int crossOrCircle;
    boolean gameOver;
    int board[][] = new int[3][3];
    int highlight = Color.argb(150, 150, 255, 255);
    int notHighlight = Color.argb(0, 150, 150, 150);

    ////////////////////////////////////////////////
    //Reseting and setting the values if phone flips
    ////////////////////////////////////////////////
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            Intent intent = getIntent();
            crossOrCircle = intent.getIntExtra("MyIntIntentKey", 0);
            gameOver = intent.getBooleanExtra("MyBoolIntentKey", false);
            board[0][0] = intent.getIntExtra("MyArrayIntentKey0", 0);
            board[0][1] = intent.getIntExtra("MyArrayIntentKey1", 0);
            board[0][2] = intent.getIntExtra("MyArrayIntentKey2", 0);
            board[1][0] = intent.getIntExtra("MyArrayIntentKey3", 0);
            board[1][1] = intent.getIntExtra("MyArrayIntentKey4", 0);
            board[1][2] = intent.getIntExtra("MyArrayIntentKey5", 0);
            board[2][0] = intent.getIntExtra("MyArrayIntentKey6", 0);
            board[2][1] = intent.getIntExtra("MyArrayIntentKey7", 0);
            board[2][2] = intent.getIntExtra("MyArrayIntentKey8", 0);
        } else { // savedInstanceState has saved values
            crossOrCircle = savedInstanceState.getInt("MyIntKey");
            gameOver = savedInstanceState.getBoolean("MyBoolKey");
            board[0][0] = savedInstanceState.getInt("MyArrayKey0");
            board[0][1] = savedInstanceState.getInt("MyArrayKey1");
            board[0][2] = savedInstanceState.getInt("MyArrayKey2");
            board[1][0] = savedInstanceState.getInt("MyArrayKey3");
            board[1][1] = savedInstanceState.getInt("MyArrayKey4");
            board[1][2] = savedInstanceState.getInt("MyArrayKey5");
            board[2][0] = savedInstanceState.getInt("MyArrayKey6");
            board[2][1] = savedInstanceState.getInt("MyArrayKey7");
            board[2][2] = savedInstanceState.getInt("MyArrayKey8");
            if(board[0][0] == 1){
                ImageButton b = (ImageButton) findViewById(R.id.imageButton00);
                b.setImageResource(R.drawable.cross);
            }
            if(board[0][0] == 2){
                ImageButton b = (ImageButton) findViewById(R.id.imageButton00);
                b.setImageResource(R.drawable.circle);
            }
            if(board[0][1] == 1){
                ImageButton b = (ImageButton) findViewById(R.id.imageButton01);
                b.setImageResource(R.drawable.cross);
            }
            if(board[0][1] == 2){
                ImageButton b = (ImageButton) findViewById(R.id.imageButton01);
                b.setImageResource(R.drawable.circle);
            }
            if(board[0][2] == 1){
                ImageButton b = (ImageButton) findViewById(R.id.imageButton02);
                b.setImageResource(R.drawable.cross);
            }
            if(board[0][2] == 2){
                ImageButton b = (ImageButton) findViewById(R.id.imageButton02);
                b.setImageResource(R.drawable.circle);
            }
            if(board[1][0] == 1){
                ImageButton b = (ImageButton) findViewById(R.id.imageButton10);
                b.setImageResource(R.drawable.cross);
            }
            if(board[1][0] == 2){
                ImageButton b = (ImageButton) findViewById(R.id.imageButton10);
                b.setImageResource(R.drawable.circle);
            }
            if(board[1][1] == 1){
                ImageButton b = (ImageButton) findViewById(R.id.imageButton11);
                b.setImageResource(R.drawable.cross);
            }
            if(board[1][1] == 2){
                ImageButton b = (ImageButton) findViewById(R.id.imageButton11);
                b.setImageResource(R.drawable.circle);
            }
            if(board[1][2] == 1){
                ImageButton b = (ImageButton) findViewById(R.id.imageButton12);
                b.setImageResource(R.drawable.cross);
            }
            if(board[1][2] == 2){
                ImageButton b = (ImageButton) findViewById(R.id.imageButton12);
                b.setImageResource(R.drawable.circle);
            }
            if(board[2][0] == 1){
                ImageButton b = (ImageButton) findViewById(R.id.imageButton20);
                b.setImageResource(R.drawable.cross);
            }
            if(board[2][0] == 2){
                ImageButton b = (ImageButton) findViewById(R.id.imageButton20);
                b.setImageResource(R.drawable.circle);
            }
            if(board[2][1] == 1){
                ImageButton b = (ImageButton) findViewById(R.id.imageButton21);
                b.setImageResource(R.drawable.cross);
            }
            if(board[2][1] == 2){
                ImageButton b = (ImageButton) findViewById(R.id.imageButton21);
                b.setImageResource(R.drawable.circle);
            }
            if(board[2][2] == 1){
                ImageButton b = (ImageButton) findViewById(R.id.imageButton22);
                b.setImageResource(R.drawable.cross);
            }
            if(board[2][2] == 2){
                ImageButton b = (ImageButton) findViewById(R.id.imageButton22);
                b.setImageResource(R.drawable.circle);
            }
            TextView d = (TextView) findViewById(R.id.player);
            if(gameOver){
                if(crossOrCircle % 2 == 0){
                    d.setText(R.string.winner2);
                } else {
                    d.setText(R.string.winner1);
                }
            }
            else{
                if(crossOrCircle % 2 == 0){
                    d.setText(R.string.playerNum1);
                } else{
                    d.setText(R.string.playerNum2);
                }
            }
            if(crossOrCircle == 9 && !gameOver){
                d.setText(R.string.tie);
            }
        }
    }
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putInt("MyIntKey", crossOrCircle);
        savedInstanceState.putBoolean("MyBoolKey", gameOver);
        savedInstanceState.putInt("MyArrayKey0", board[0][0]);
        savedInstanceState.putInt("MyArrayKey1", board[0][1]);
        savedInstanceState.putInt("MyArrayKey2", board[0][2]);
        savedInstanceState.putInt("MyArrayKey3", board[1][0]);
        savedInstanceState.putInt("MyArrayKey4", board[1][1]);
        savedInstanceState.putInt("MyArrayKey5", board[1][2]);
        savedInstanceState.putInt("MyArrayKey6", board[2][0]);
        savedInstanceState.putInt("MyArrayKey7", board[2][1]);
        savedInstanceState.putInt("MyArrayKey8", board[2][2]);
        super.onSaveInstanceState(savedInstanceState);
    }
    //////////////////////////////
    //Pressing the new game button
    //////////////////////////////
    public void newGame(View view) {
        TextView d = (TextView) findViewById(R.id.player);
        for(int i = 0; i < board[0].length; i++){
            for(int j = 0; j < board[0].length; j++){
                board[i][j] = 0;
            }
        }
        crossOrCircle = 0;
        gameOver = false;
        d.setText(R.string.playerNum1);
        ImageButton b = (ImageButton) findViewById(R.id.imageButton00);
        b.setImageResource(R.drawable.nothing);
        b.setColorFilter(notHighlight);
        b = (ImageButton) findViewById(R.id.imageButton01);
        b.setImageResource(R.drawable.nothing);
        b.setColorFilter(notHighlight);
        b = (ImageButton) findViewById(R.id.imageButton02);
        b.setImageResource(R.drawable.nothing);
        b.setColorFilter(notHighlight);
        b = (ImageButton) findViewById(R.id.imageButton10);
        b.setImageResource(R.drawable.nothing);
        b.setColorFilter(notHighlight);
        b = (ImageButton) findViewById(R.id.imageButton11);
        b.setImageResource(R.drawable.nothing);
        b.setColorFilter(notHighlight);
        b = (ImageButton) findViewById(R.id.imageButton12);
        b.setImageResource(R.drawable.nothing);
        b.setColorFilter(notHighlight);
        b = (ImageButton) findViewById(R.id.imageButton20);
        b.setImageResource(R.drawable.nothing);
        b.setColorFilter(notHighlight);
        b = (ImageButton) findViewById(R.id.imageButton21);
        b.setImageResource(R.drawable.nothing);
        b.setColorFilter(notHighlight);
        b = (ImageButton) findViewById(R.id.imageButton22);
        b.setImageResource(R.drawable.nothing);
        b.setColorFilter(notHighlight);
    }
    ///////////////////////
    //Clicking on the board
    ///////////////////////
    public void clickBoard(View view){
        // get the tag
        //String t = (String) view.getTag();
        TextView d = (TextView) findViewById(R.id.player);
        ImageView button;
        String rowNCol = view.getContentDescription().toString();
        char row = rowNCol.charAt(0);
        char col = rowNCol.charAt(1);
        if(!gameOver && board[Character.getNumericValue(row)][Character.getNumericValue(col)] == 0){
            // If you want to put a cross or circle on it.
            ImageButton vv = (ImageButton) view;
            if (crossOrCircle % 2 == 0) {
                vv.setImageResource(R.drawable.cross);
                board[Character.getNumericValue(row)][Character.getNumericValue(col)] = 1;
            } else {
                vv.setImageResource(R.drawable.circle);
                board[Character.getNumericValue(row)][Character.getNumericValue(col)] = 2;
            }
            crossOrCircle++;
        }
        //////////////////////
        //game over conditions
        //////////////////////
        //***
        //000
        //000
        if((board[0][0] == 1 || board[0][0] == 2) && board[0][0] == board[0][1] && board[0][1] == board[0][2]){
            button = (ImageView) findViewById(R.id.imageButton00);
            button.setColorFilter(highlight);
            button = (ImageView) findViewById(R.id.imageButton01);
            button.setColorFilter(highlight);
            button = (ImageView) findViewById(R.id.imageButton02);
            button.setColorFilter(highlight);
            gameOver = true;
        }
        //000
        //***
        //000
        else if((board[1][0] == 1 || board[1][0] == 2) && board[1][0] == board[1][1] && board[1][1] == board[1][2]){
            button = (ImageView) findViewById(R.id.imageButton10);
            button.setColorFilter(highlight);
            button = (ImageView) findViewById(R.id.imageButton11);
            button.setColorFilter(highlight);
            button = (ImageView) findViewById(R.id.imageButton12);
            button.setColorFilter(highlight);
            gameOver = true;
        }
        //000
        //000
        //***
        else if((board[2][0] == 1 || board[2][0] == 2) && board[2][0] == board[2][1] && board[2][1] == board[2][2]){
            button = (ImageView) findViewById(R.id.imageButton20);
            button.setColorFilter(highlight);
            button = (ImageView) findViewById(R.id.imageButton21);
            button.setColorFilter(highlight);
            button = (ImageView) findViewById(R.id.imageButton22);
            button.setColorFilter(highlight);
            gameOver = true;
        }
        //*00
        //*00
        //*00
        else if((board[0][0] == 1 || board[0][0] == 2) && board[0][0] == board[1][0] && board[1][0] == board[2][0]){
            button = (ImageView) findViewById(R.id.imageButton00);
            button.setColorFilter(highlight);
            button = (ImageView) findViewById(R.id.imageButton10);
            button.setColorFilter(highlight);
            button = (ImageView) findViewById(R.id.imageButton20);
            button.setColorFilter(highlight);
            gameOver = true;
        }
        //0*0
        //0*0
        //0*0
        else if((board[0][1] == 1 || board[0][1] == 2) && board[0][1] == board[1][1] && board[1][1] == board[2][1]){
            button = (ImageView) findViewById(R.id.imageButton01);
            button.setColorFilter(highlight);
            button = (ImageView) findViewById(R.id.imageButton11);
            button.setColorFilter(highlight);
            button = (ImageView) findViewById(R.id.imageButton21);
            button.setColorFilter(highlight);
            gameOver = true;
        }
        //00*
        //00*
        //00*
        else if((board[0][2] == 1 || board[0][2] == 2) && board[0][2] == board[1][2] && board[1][2] == board[2][2]){
            button = (ImageView) findViewById(R.id.imageButton02);
            button.setColorFilter(highlight);
            button = (ImageView) findViewById(R.id.imageButton12);
            button.setColorFilter(highlight);
            button = (ImageView) findViewById(R.id.imageButton22);
            button.setColorFilter(highlight);
            gameOver = true;
        }
        //*00
        //0*0
        //00*
        else if((board[0][0] == 1 || board[0][0] == 2) && board[0][0] == board[1][1] && board[1][1] == board[2][2]){
            button = (ImageView) findViewById(R.id.imageButton00);
            button.setColorFilter(highlight);
            button = (ImageView) findViewById(R.id.imageButton11);
            button.setColorFilter(highlight);
            button = (ImageView) findViewById(R.id.imageButton22);
            button.setColorFilter(highlight);
            gameOver = true;
        }
        //00*
        //0*0
        //*00
        else if((board[0][2] == 1 || board[0][2] == 2) && board[0][2] == board[1][1] && board[1][1] == board[2][0]){
            button = (ImageView) findViewById(R.id.imageButton02);
            button.setColorFilter(highlight);
            button = (ImageView) findViewById(R.id.imageButton11);
            button.setColorFilter(highlight);
            button = (ImageView) findViewById(R.id.imageButton20);
            button.setColorFilter(highlight);
            gameOver = true;
        }
        //////////////////
        //print statements
        //////////////////
        if(gameOver){
            if(crossOrCircle % 2 == 0){
                d.setText(R.string.winner2);
            } else {
                d.setText(R.string.winner1);
            }
        }
        else{
            if(crossOrCircle % 2 == 0){
                d.setText(R.string.playerNum1);
            } else{
                d.setText(R.string.playerNum2);
            }
        }
        if(crossOrCircle == 9 && !gameOver){
            d.setText(R.string.tie);
        }
    }
}
