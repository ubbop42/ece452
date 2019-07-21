package com.example.thumbtrainer;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import java.util.Random;

import swipView.GameFragment;

import static android.widget.Toast.LENGTH_SHORT;

public class SwipeActivity extends AppCompatActivity implements GameFragment.OnGameOver {
    int counter = 0;
    TextView counterText;
    TextView timeText;
    ImageView image;
    private float x1,x2,y1,y2;
    static final int MIN_DISTANCE = 150;
    String currGesture;
    String[] gestures = {"up","down","left","right","o"};
    final Random random = new Random();
    final int length = gestures.length;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe);
        counterText = findViewById(R.id.counter);
        timeText = findViewById(R.id.timer);
        image = findViewById(R.id.action_image);

        MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.hmk);
        mp.start();

//        new CountDownTimer(150000, 1000) {
//
//            public void onTick(long millisUntilFinished) {
//                timeText.setText("" + millisUntilFinished / 1000);
//            }
//
//            public void onFinish() {
//                // TODO: launch leaderboard
//            }
//        }.start();
//
//        nextGesture();

        GameFragment gameFragment = new GameFragment();

        FragmentTransaction transaction = (FragmentTransaction) getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, gameFragment, "Game");
        transaction.addToBackStack("Game");
        transaction.commit();
    }

    public void onGameOver(int score) {
        //TODO
        Toast.makeText(this, "score"+score, LENGTH_SHORT).show ();
    }

//    public void nextGesture(){
//        currGesture = gestures[random.nextInt(length)];
//        Toast.makeText(this, currGesture, LENGTH_SHORT).show ();
//        counterText.setText(counter+"");
//        counter++;
//        if(currGesture.equals("left")){
//            image.setImageResource(R.drawable.left);
//        } else  if(currGesture.equals("right")){
//            image.setImageResource(R.drawable.left);
//        } else if(currGesture.equals("up")){
//            image.setImageResource(R.drawable.left);
//        } else if(currGesture.equals("down")){
//            image.setImageResource(R.drawable.left);
//        } else if(currGesture.equals("o")){
//            image.setImageResource(R.drawable.left);
//        }
//        image.setColorFilter(Color.GRAY, PorterDuff.Mode.SRC_ATOP);
//    }
//
//    public boolean onTouchEvent(MotionEvent event)
//    {
//        switch(event.getAction())
//        {
//            case MotionEvent.ACTION_DOWN:
//                x1 = event.getX();
//                y1 = event.getY();
//                break;
//            case MotionEvent.ACTION_UP:
//                x2 = event.getX();
//                y2 = event.getY();
//                float deltaX = x2 - x1;
//                float deltaY = y2 - y1;
//                if (Math.abs(deltaX) > MIN_DISTANCE)
//                {
//                    // Left to Right swipe action
//                    if (x2 > x1 && currGesture.equals("right"))
//                    {
//                        Toast.makeText(this, "Right swipe", LENGTH_SHORT).show ();
//                        nextGesture();
//                    }
//
//                    // Right to left swipe action
//                    else if(x2 < x1 && currGesture.equals("left"))
//                    {
//                        Toast.makeText(this, "Left swipe", LENGTH_SHORT).show ();
//                        nextGesture();
//
//                    }
//                }
//                else if (Math.abs(deltaY) > MIN_DISTANCE)
//                {
//                    // Left to Right swipe action
//                    if (y2 > y1 && currGesture.equals("down"))
//                    {
//                        Toast.makeText(this, "Down swipe", LENGTH_SHORT).show ();
//                        nextGesture();
//
//                    }
//
//                    // Right to left swipe action
//                    else if(y2 < y1 && currGesture.equals("up"))
//                    {
//                        Toast.makeText(this, "Up swipe", LENGTH_SHORT).show ();
//                        nextGesture();
//                    }
//                }
//                else if (Math.abs(deltaY) < MIN_DISTANCE && Math.abs(deltaX) < MIN_DISTANCE && currGesture.equals("o"))
//                {
//                    Toast.makeText(this, "o", LENGTH_SHORT).show ();
//                    nextGesture();
//                }
//                break;
//        }
//        return super.onTouchEvent(event);
//    }

}
