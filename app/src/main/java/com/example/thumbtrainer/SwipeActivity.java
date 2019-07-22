package com.example.thumbtrainer;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import java.util.Random;

import swipView.GameFragment;

import static android.widget.Toast.LENGTH_SHORT;

public class SwipeActivity extends AppCompatActivity implements GameFragment.OnGameOver {
    Context context = this;
    int counter = 0;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe);
        GameFragment gameFragment = new GameFragment();

        new CountDownTimer(60000, 1000) {

            public void onTick(long millisUntilFinished) {
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
                                    public void onClick(DialogInterface dialog, int id) {
                                        // get user input and set it to result
                                        // edit text
                                        String user =userInput.getText().toString();
                                        Intent intent = new Intent(getBaseContext(), LeaderboardActivity.class);
                                        intent.putExtra("USER", user);
                                        intent.putExtra("SCORE", counter);
                                        intent.putExtra("GAMEMODE", "SwipeActivity");
                                        startActivity(intent);
                                    }
                                })
                        .setNegativeButton("Cancel",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                });

                AlertDialog alertDialog = alertDialogBuilder.create();

                alertDialog.show();
            }
        };

        FragmentTransaction transaction = (FragmentTransaction) getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, gameFragment, "Game");
        transaction.addToBackStack("Game");
        transaction.commit();
    }

    public void onGameOver(int score) {

        LayoutInflater li = LayoutInflater.from(context);
        View promptsView = li.inflate(R.layout.prompts, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setView(promptsView);
        final EditText userInput = (EditText) promptsView.findViewById(R.id.editTextDialogUserInput);

        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // get user input and set it to result
                                // edit text
                                String user =userInput.getText().toString();
                                Intent intent = new Intent(getBaseContext(), LeaderboardActivity.class);
                                intent.putExtra("USER", user);
                                intent.putExtra("SCORE", counter);
                                intent.putExtra("GAMEMODE", "SwipeActivity");
                                startActivity(intent);
                            }
                        })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

        AlertDialog alertDialog = alertDialogBuilder.create();

        alertDialog.show();
    }

}
