package com.example.thumbtrainer;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import androidx.appcompat.app.AppCompatActivity;
import patternView.patternView;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class PatternActivity extends AppCompatActivity {
    int counter = 0;
    private patternView patternView;
    ArrayList<String> listOfPatterns = new ArrayList<>();
    TextView counterText;
    TextView timeText;
    boolean isClassic;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pattern);

        Intent intent = getIntent();

        isClassic = intent.getBooleanExtra("isClassic",true);

        counterText = findViewById(R.id.counter);
        timeText = findViewById(R.id.timer);
        patternView = findViewById(R.id.lock_9_view);

        if(isClassic) {
            timeText.setVisibility(View.INVISIBLE);
            counterText.setText("0");
        } else{
            MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.hmk);
            mp.start();
            counterText.setText("0");
            new CountDownTimer(1500, 1000) {

                public void onTick(long millisUntilFinished) {
                    timeText.setText("" + millisUntilFinished / 1000);
                }

                public void onFinish() {
                    Intent intent = new Intent(getBaseContext(), LeaderboardActivity.class);
                    intent.putExtra("SCORE",counter);
                    intent.putExtra("GAMEMODE", "PatternActivity");
                    startActivity(intent);
                }
            }.start();
        }

        View view = findViewById(R.id.patternView);
        view.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View view,MotionEvent event) {
                float size = event.getSize();

                Toast.makeText(view.getContext(), ""+size, Toast.LENGTH_SHORT).show();
                return true;

            }
        });

        patternView.setCallBack(new patternView.CallBack() {

            @Override
            public void onFinish(String password) {
                if(listOfPatterns.contains(password) || password.length() < 3){
                } else{
                    listOfPatterns.add(password);
                    counter++;
                    counterText.setText(counter+"");
                    if(counter == 389112){
                        //TODO: launch leaderboard
                    }
                }
            }
        });

    }

    @Override
    public void onBackPressed() {
        if (isClassic) {
            super.onBackPressed();
        } else {
            Toast.makeText(this, "can't go back now", Toast.LENGTH_SHORT).show();
        }
    }

}