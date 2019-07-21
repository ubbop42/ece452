package com.example.thumbtrainer;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

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
    EditText textBox;
    TextView timeText;
    String toType;
    int length;
    String[] words;
    int index;

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

        text.setText(toType);
        textBox = (EditText)findViewById(R.id.plain_text_input);
        timeText = findViewById(R.id.timer);
        textBox.setVisibility(View.VISIBLE);


        MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.hmk);
        mp.start();

        new CountDownTimer(150000, 1000) {

            public void onTick(long millisUntilFinished) {
                timeText.setText("" + millisUntilFinished / 1000);
            }

            public void onFinish() {
                // TODO: launch leaderboard
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
                    text.setText(toType);
                    textBox.setText(null);
                    counter++;
                }
            }
        });

    }
}