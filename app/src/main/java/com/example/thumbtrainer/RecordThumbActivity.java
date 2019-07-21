package com.example.thumbtrainer;

import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.view.View.OnTouchListener;
import android.view.MotionEvent;
import android.util.Log;
import android.widget.TextView;
import java.util.Map;
import android.content.Intent;

public class RecordThumbActivity extends AppCompatActivity {
    int leftCount = 0;
    int rightCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final SharedPreferences preferences = getPreferences(MODE_PRIVATE);
        final SharedPreferences.Editor editor = preferences.edit();

        boolean leftThumbRecorded = preferences.contains("leftThumb");
        boolean rightThumbRecorded = preferences.contains("rightThumb");
        boolean thumbRecorded = leftThumbRecorded && rightThumbRecorded;

        if (thumbRecorded) {
            Intent gotoMainMenu = new Intent(this, MainActivity.class);
            startActivity(gotoMainMenu);
        }


        setContentView(R.layout.activity_record_thumb);

        View contentView = findViewById(R.id.content_record_thumb);
        final Button leftButton = contentView.findViewById(R.id.left_button);
        final Button rightButton = contentView.findViewById(R.id.right_button);
        final TextView instructions = contentView.findViewById(R.id.thumb_detection_instruction);
        final String updatedInstruction = "Press the button on the right 10 times with your thumb";

        leftButton.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch (View v, MotionEvent e) {
                if (e.getAction() == 0 && leftCount < 10) {
                    float size = e.getSize();
                    if (!(preferences.contains("leftThumb")) || e.getSize() < preferences.getFloat("leftThumb", 1)) {
                        editor.putFloat("leftThumb", size);
                        editor.apply();
                    }
                    leftCount++;
                    leftButton.setText(Integer.toString(leftCount));
                }
                if (leftCount == 10) {
                    instructions.setText(updatedInstruction);
                    rightButton.setText("0");
                }
                return false;
            }
        });

        rightButton.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch (View v, MotionEvent e) {
                if (e.getAction() == 0 && rightCount < 10) {
                    float size = e.getSize();
                    if (!(preferences.contains("rightThumb")) || e.getSize() < preferences.getFloat("rightThumb", 1)) {
                        editor.putFloat("rightThumb", size);
                        editor.apply();
                    }
                    rightCount++;
                    rightButton.setText(Integer.toString(rightCount));
                }
                if (rightCount == 10) {
                    Intent toMainMenu = new Intent(v.getContext(), MainActivity.class);
                    startActivity(toMainMenu);
                }
                return false;
            }
        });
    }

}
