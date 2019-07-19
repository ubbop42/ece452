package com.example.thumbtrainer;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ToggleButton;
import android.media.MediaPlayer;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View contentView = findViewById(R.id.content_main);
        final ToggleButton soundButton = contentView.findViewById(R.id.toggle_button);
        soundButton.setChecked(true);

        final MediaPlayer mp = MediaPlayer.create(this, R.raw.game_music);
        mp.setLooping(true);
        mp.start();

        soundButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (soundButton.isChecked()) {
                    mp.seekTo(0);
                    mp.start();
                } else {
                    mp.pause();
                }
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void toggleSound (View view) {
        boolean on = ((ToggleButton) view).isChecked();

        MediaPlayer mp = MediaPlayer.create(this, R.raw.game_music);

        if (on) {
            mp.start();
            mp.setLooping(true);
        } else {
            mp.stop();
            mp.release();
        }
    }
}
