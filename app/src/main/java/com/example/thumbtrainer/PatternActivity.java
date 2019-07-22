package com.example.thumbtrainer;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import androidx.appcompat.app.AppCompatActivity;
import patternView.patternView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class PatternActivity extends AppCompatActivity {
    Context context = this;
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

        final SharedPreferences preferences = getPreferences(MODE_PRIVATE);
        final SharedPreferences.Editor editor = preferences.edit();

        float rightThumb = preferences.getFloat("rightThumb", 0.020f);
        float leftThumb = preferences.getFloat("leftThumb", 0.020f);

        Toast.makeText(this, "r:"+rightThumb +"l:"+leftThumb, Toast.LENGTH_LONG).show();


        Intent intent = getIntent();

        isClassic = intent.getBooleanExtra("isClassic",true);

        counterText = findViewById(R.id.counter);
        timeText = findViewById(R.id.timer);
        patternView = findViewById(R.id.lock_9_view);

        if(isClassic) {
            timeText.setVisibility(View.INVISIBLE);
            counterText.setText("0");
        } else{
            counterText.setText("0");
            new CountDownTimer(60000, 1000) {

                public void onTick(long millisUntilFinished) {
                    timeText.setText("" + millisUntilFinished / 1000);
                }

                public void onFinish() {

                    LayoutInflater li = LayoutInflater.from(context);
                    View promptsView = li.inflate(R.layout.prompts, null);

                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                    alertDialogBuilder.setView(promptsView);
                    final EditText userInput = (EditText) promptsView.findViewById(R.id.editTextDialogUserInput);

                    alertDialogBuilder
                            .setCancelable(false)
                            .setPositiveButton("OK",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog,int id) {
                                            // get user input and set it to result
                                            // edit text
                                            String user = userInput.getText().toString();
                                            Intent intent = new Intent(getBaseContext(), LeaderboardActivity.class);
                                            intent.putExtra("USER", user);
                                            intent.putExtra("SCORE",counter);
                                            intent.putExtra("GAMEMODE", "PatternActivity");
                                            startActivity(intent);
                                        }
                                    })
                            .setNegativeButton("Cancel",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog,int id) {
                                            dialog.cancel();
                                        }
                                    });

                    AlertDialog alertDialog = alertDialogBuilder.create();

                    // show it
                    alertDialog.show();
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
                        LayoutInflater li = LayoutInflater.from(context);
                        View promptsView = li.inflate(R.layout.prompts, null);

                        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                        alertDialogBuilder.setView(promptsView);
                        final EditText userInput = (EditText) promptsView.findViewById(R.id.editTextDialogUserInput);

                        alertDialogBuilder
                                .setCancelable(false)
                                .setPositiveButton("OK",
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog,int id) {
                                                // get user input and set it to result
                                                // edit text
                                                String user = userInput.getText().toString();
                                                Intent intent = new Intent(getBaseContext(), LeaderboardActivity.class);
                                                intent.putExtra("USER", user);
                                                intent.putExtra("SCORE",counter);
                                                intent.putExtra("GAMEMODE", "PatternActivity");
                                                startActivity(intent);
                                            }
                                        })
                                .setNegativeButton("Cancel",
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog,int id) {
                                                dialog.cancel();
                                            }
                                        });

                        AlertDialog alertDialog = alertDialogBuilder.create();

                        // show it
                        alertDialog.show();
                    }
                }
            }
        });

    }

}