package com.example.thumbtrainer;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import androidx.appcompat.app.AppCompatActivity;
import patternView.patternView;

import android.os.Bundle;
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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pattern);

        Intent intent = getIntent();

        boolean isClassic = intent.getBooleanExtra("isClassic",true);

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
            new CountDownTimer(150000, 1000) {

                public void onTick(long millisUntilFinished) {
                    timeText.setText("" + millisUntilFinished / 1000);
                }

                public void onFinish() {
                   // TODO: launch leaderboard
                }
            }.start();
        }

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
}