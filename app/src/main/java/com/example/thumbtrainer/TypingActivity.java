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

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;
import android.util.Log;
import java.util.Random;

public class TypingActivity extends AppCompatActivity {
    int counter = 0;
    private patternView patternView;
    ArrayList<String> listOfPatterns = new ArrayList<>();
    TextView timeText;
    TextView text;

    EditText textBox;
    final String[] proper_noun = {"Fred", "Jane", "Nixon", "Miss"};
    long startTime = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.typing);

        Intent intent = getIntent();

        boolean isClassic = intent.getBooleanExtra("isClassic",true);

        text = findViewById(R.id.text);
        timeText = findViewById(R.id.timer);

        final Random random = new Random();
        int index = random.nextInt(proper_noun.length);
        final String initalText = proper_noun[index];

        //text.setText(toType);
        text.setText(proper_noun[index]);
        textBox = (EditText)findViewById(R.id.plain_text_input);
        textBox.setVisibility(View.VISIBLE);

        startTime = System.currentTimeMillis();

        textBox.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
            }

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            String currentText = null;
            int index;

            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                Log.d("text box",""+s);

                Log.d("text",""+initalText);
                Log.d("text",""+currentText);

                if(s.toString().equals(initalText)) {
                    Random random = new Random();
                    index = random.nextInt(proper_noun.length);
                    currentText = proper_noun[index];
                    text.setText(proper_noun[index]);
                    textBox.setText(null);
                    counter++;
                }

                if(s.toString().equals(currentText)){
                    Random random = new Random();
                    index = random.nextInt(proper_noun.length);
                    currentText = proper_noun[index];
                    text.setText(proper_noun[index]);
                    textBox.setText(null);
                    counter++;

                    if(counter == 5) {
                        double time = (System.currentTimeMillis() - startTime)/1000.0;
                        textBox.setVisibility(View.INVISIBLE);
                        text.setText("Your Time: "+ time);
                    }
                }
            }
        });

    }
}