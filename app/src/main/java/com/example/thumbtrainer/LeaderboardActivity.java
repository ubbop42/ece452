package com.example.thumbtrainer;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.List;
import java.util.ArrayList;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.example.thumbtrainer.leaderboardLogic.CustomListAdapter;
import com.example.thumbtrainer.leaderboardLogic.Items;

import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.content.DialogInterface;
import android.app.AlertDialog;



public class LeaderboardActivity extends AppCompatActivity {

    Intent intent = getIntent();

    String gamemode;
    int highscore;
    String username;

    private List<Items> itemsList = new ArrayList<Items>();
    private ListView listView;
    private CustomListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);


        Intent intent = getIntent();

        gamemode = intent.getStringExtra("GAMEMODE");
        highscore = intent.getIntExtra("SCORE", 0);
        username = intent.getStringExtra("USER");
        //username = "Harsh";
        SQLiteDatabase myDB = null;

        try {
                myDB = this.openOrCreateDatabase("leaderboard", MODE_PRIVATE, null);

                Cursor cursor;
                if (gamemode.equals("PatternActivity")) {
                    myDB.execSQL("CREATE TABLE IF NOT EXISTS scores_pattern (name TEXT, score INT);");
                    cursor = myDB.rawQuery("SELECT * FROM scores_pattern", null);

                } else if (gamemode.equals("TypingActivity")) {
                    myDB.execSQL("CREATE TABLE IF NOT EXISTS scores_typing (name TEXT, score INT);");
                    cursor = myDB.rawQuery("SELECT * FROM scores_typing", null);
                } else {
                    myDB.execSQL("CREATE TABLE IF NOT EXISTS scores_swipe (name TEXT, score INT);");
                    cursor = myDB.rawQuery("SELECT * FROM scores_swipe", null);
                }

                //REPLACE gamemode with name when launching issue is resolved
            if (gamemode.equals("PatternActivity")) {
                myDB.execSQL("INSERT INTO scores_pattern (name, score) VALUES ('" + username + "', " + highscore + ");");
            }  else if (gamemode.equals("TypingActivity")) {
                myDB.execSQL("INSERT INTO scores_typing (name, score) VALUES ('" + username + "', " + highscore + ");");
            } else {
                myDB.execSQL("INSERT INTO scores_swipe (name, score) VALUES ('" + username + "', " + highscore + ");");
            }

        } catch (Exception e) {

        } finally {


            listView = (ListView) findViewById(R.id.list);
            adapter = new CustomListAdapter(this, itemsList);
            listView.setAdapter(adapter);


            Cursor cursor;
            if (gamemode.equals("PatternActivity")) {
                cursor = myDB.rawQuery("SELECT * FROM scores_pattern ORDER BY score DESC", null);
            }  else if (gamemode.equals("TypingActivity")) {
                cursor = myDB.rawQuery("SELECT * FROM scores_typing ORDER BY score DESC", null);
            } else {
                cursor = myDB.rawQuery("SELECT * FROM scores_swipe ORDER BY score DESC", null);
            }
            if (cursor.moveToFirst()) {

                //read all rows from the database and add to the Items array

                while (!cursor.isAfterLast()) {

                    Items items = new Items();

                    items.setName(cursor.getString(0));
                    items.setScore(cursor.getString(1));

                    itemsList.add(items);
                    cursor.moveToNext();


                }
            }

            //All done, so notify the adapter to populate the list using the Items Array

            adapter.notifyDataSetChanged();
        }
    }

}


