package com.example.syaokyo.my2048;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Button again;
    private GameView gameView;
    private TextView score;
    private int sumScore;
    private static MainActivity mainActivity=null;
    public MainActivity(){
        mainActivity=this;
    }
    public static MainActivity getMainActivity(){
        return mainActivity;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        score=(TextView) findViewById(R.id.score);
        setContentView(R.layout.activity_main);
        again= (Button) findViewById(R.id.again);
        gameView= (GameView) findViewById(R.id.gameView);
        again.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameView.startGame();
            }
        });
    }

    public void clearScore(){
        sumScore=0;
        showScore();
    }

    public void showScore() {
        score=(TextView) findViewById(R.id.score);
        score.setText(sumScore+"");
    }

    public void addScore(int s){
        sumScore+=s;
        showScore();
    }
}
