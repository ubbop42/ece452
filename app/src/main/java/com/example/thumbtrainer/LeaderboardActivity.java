package com.example.thumbtrainer;

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

import android.view.View;
import android.widget.ListView;

public class LeaderboardActivity extends AppCompatActivity {

    Intent intent = getIntent();
    String gamemode = intent.getStringExtra("GAMEMODE");
    String highscore = Integer.toString(intent.getIntExtra("SCORE", 0));

    private List<Items> itemsList = new ArrayList<Items>();
    private ListView listView;
    private CustomListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);
        SQLiteDatabase myDB = null;

        try {
                myDB = this.openOrCreateDatabase("leaderboard", MODE_PRIVATE, null);

                Cursor cursor;
                if (gamemode.equals("PatternActivity")) {
                    myDB.execSQL("CREATE TABLE IF NOT EXISTS scores_pattern (name TEXT, score TEXT);");
                    cursor = myDB.rawQuery("SELECT * FROM scores_pattern", null);

                } else if (gamemode.equals("TypingActivity")) {
                    myDB.execSQL("CREATE TABLE IF NOT EXISTS scores_typing (name TEXT, score TEXT);");
                    cursor = myDB.rawQuery("SELECT * FROM scores_typing", null);
                } else {
                    myDB.execSQL("CREATE TABLE IF NOT EXISTS scores_swipe (name TEXT, score TEXT);");
                    cursor = myDB.rawQuery("SELECT * FROM scores_swipe", null);
                }

                //REPLACE gamemode with name when launching issue is resolved
            if (gamemode.equals("PatternActivity")) {
                myDB.execSQL("INSERT INTO scores_pattern (name, score) VALUES ('" + gamemode + "', '" + highscore + "');");
            }  else if (gamemode.equals("TypingActivity")) {
                myDB.execSQL("INSERT INTO scores_typing (name, score) VALUES ('" + gamemode + "', '" + highscore + "');");
            } else {
                myDB.execSQL("INSERT INTO scores_swipe (name, score) VALUES ('" + gamemode + "', '" + highscore + "');");
            }

        } catch (Exception e) {

        } finally {


            listView = (ListView) findViewById(R.id.list);
            adapter = new CustomListAdapter(this, itemsList);
            listView.setAdapter(adapter);


            Cursor cursor;
            if (gamemode.equals("PatternActivity")) {
                cursor = myDB.rawQuery("SELECT * FROM scores_pattern", null);
            }  else if (gamemode.equals("TypingActivity")) {
                cursor = myDB.rawQuery("SELECT * FROM scores_typing", null);
            } else {
                cursor = myDB.rawQuery("SELECT * FROM scores_swipe", null);
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
