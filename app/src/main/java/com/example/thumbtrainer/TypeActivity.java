//package com.example.thumbtrainer;
//import android.content.Intent;
//import android.media.MediaPlayer;
//import android.os.CountDownTimer;
//import androidx.appcompat.app.AppCompatActivity;
//import patternView.patternView;
//
//import android.os.Bundle;
//import android.view.View;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import java.util.ArrayList;
//
//public class PatternActivity extends AppCompatActivity {
//    int counter = 0;
//    private patternView patternView;
//    ArrayList<String> listOfPatterns = new ArrayList<>();
//    TextView counterText;
//    TextView timeText;
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.pattern);
//
//        Intent intent = getIntent();
//
//        counterText = findViewById(R.id.counter);
//        timeText = findViewById(R.id.timer);
//        patternView = findViewById(R.id.lock_9_view);
//
//
//            public void afterTextChanged(Editable s) {
//            }
//
//            public void beforeTextChanged(CharSequence s, int start,
//                                          int count, int after) {
//            }
//
//            public void onTextChanged(CharSequence s, int start,
//                                      int before, int count) {
//                Log.d("text box",""+s);
//                if(s.toString().equals(toType) && stage == 5){
//                    double time = (System.currentTimeMillis() - startTime)/1000.0;
//                    textBox.setVisibility(View.INVISIBLE);
//                    text.setText("Your Time: "+ time);
//                }
//            }
//        });
//        }
//
//        patternView.setCallBack(new patternView.CallBack() {
//
//            @Override
//            public void onFinish(String password) {
//                if(listOfPatterns.contains(password) || password.length() < 3){
//                } else{
//                    listOfPatterns.add(password);
//                    counter++;
//                    counterText.setText(counter+"");
//                    if(counter == 389112){
//                        //TODO: launch leaderboard
//                    }
//                }
//            }
//        });
//
//    }
//}