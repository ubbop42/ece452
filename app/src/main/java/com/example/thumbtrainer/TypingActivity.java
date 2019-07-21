package com.example.thumbtrainer;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextWatcher;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.graphics.Typeface;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.view.Gravity;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TypingActivity extends AppCompatActivity {
    int counter = 0;
    TextView text;
    TextView score;
    EditText textBox;
    TextView timeText;
    String toType;
    int length;
    String[] words;
    int index;
    int points = 0;

    public String[] getWords()  throws IOException{
        BufferedReader reader = null;
        List<String> lines = new ArrayList<>();
        reader = new BufferedReader(
                new InputStreamReader(getAssets().open("words.txt"), "UTF-8"));

        String mLine;
        while ((mLine = reader.readLine()) != null) {
            lines.add(mLine);
        }
        return lines.toArray(new String[lines.size()]);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.typing);

        try {
            words = getWords();
        } catch (IOException e) {
            e.printStackTrace();
        }

        length = words.length;

        text = findViewById(R.id.text);

        final Random random = new Random();
        index = random.nextInt(length);

        toType=words[index];
        SpannableString ss = new SpannableString("Enter Word : "+toType);
        StyleSpan italicSpan = new StyleSpan(Typeface.ITALIC);
        ss.setSpan(italicSpan, 11, ss.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ss.setSpan(new RelativeSizeSpan(0.7f), 0,12, 0);
        ss.setSpan(new ForegroundColorSpan(Color.GRAY), 0, 12, 0);

        text.setText(ss);
        textBox = (EditText)findViewById(R.id.plain_text_input);
        timeText = findViewById(R.id.timer);
        textBox.setVisibility(View.VISIBLE);

        score = findViewById(R.id.score);
        score.setText("Score : "+points);


        MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.hmk);
        mp.start();

        new CountDownTimer(30000, 1000) {

            public void onTick(long millisUntilFinished) {
                timeText.setText("Time Left : " + millisUntilFinished / 1000);
            }

            public void onFinish() {
                LayoutInflater inflater = (LayoutInflater)
                        getSystemService(LAYOUT_INFLATER_SERVICE);
                View popupView = inflater.inflate(R.layout.popup_window, null);

                int width = 1200;
                int height = 1600;
                boolean focusable = true;
                final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

                TextView score;
                TextView words;
                TextView error;
                score = popupView.findViewById(R.id.score);
                words = popupView.findViewById(R.id.time);
                score.setText("Score : "+points);
                words.setText("WPM : "+counter*2);

                Button menuButton;
                Button restartButton;
                menuButton = popupView.findViewById(R.id.menuButton);
                restartButton = popupView.findViewById(R.id.restartButton);

                menuButton.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        Intent intent = new Intent(v.getContext(), MainActivity.class);
                        startActivity(intent);
                    }
                });

                restartButton.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        Intent intent = new Intent(v.getContext(), TypingActivity.class);
                        startActivity(intent);
                    }
                });

                popupWindow.showAtLocation(popupView, Gravity.CENTER, 0, 0);
            }
        }.start();



        textBox.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
            }

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {

                if(s.toString().equalsIgnoreCase(toType)) {
                    Random random = new Random();
                    index = random.nextInt(length);
                    toType = words[index];
                    points = points + 100;

                    SpannableString ss = new SpannableString("Enter Word : "+toType);
                    StyleSpan italicSpan = new StyleSpan(Typeface.ITALIC);
                    ss.setSpan(italicSpan, 11, ss.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    ss.setSpan(new RelativeSizeSpan(0.7f), 0,12, 0);
                    ss.setSpan(new ForegroundColorSpan(Color.WHITE), 0, 12, 0);

                    text.setText(ss);
                    textBox.setText(null);
                    score.setText("Score : " + points);
                    counter++;
                }
            }
        });

    }
}