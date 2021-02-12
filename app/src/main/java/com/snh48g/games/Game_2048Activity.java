package com.snh48g.games;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.snh48g.games.entity.Card;

import java.util.Random;

import static com.snh48g.games.view.GameView.OnClickListener;

public class Game_2048Activity extends AppCompatActivity implements OnClickListener, CompoundButton.OnCheckedChangeListener {

    View game;
    TextView score, best_score, tips;
    Button help, exit;
    CheckBox hidden_play;
    SharedPreferences sharedPreferences;
    static Game_2048Activity game_2048Activity = null;
    int Score = 0;
    int Best_Score = 0;
    static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2048);
        DisplayMetrics displayMetrics = getApplicationContext().getResources().getDisplayMetrics();
        game = findViewById(R.id.game_2048);
        score = findViewById(R.id.score);
        best_score = findViewById(R.id.best_score);
        tips = findViewById(R.id.tips);
        exit = findViewById(R.id.exit);
        help = findViewById(R.id.help);
        hidden_play = findViewById(R.id.hidden_play);
        ViewGroup.LayoutParams laParams = (ViewGroup.LayoutParams)game.getLayoutParams();
        laParams.width = displayMetrics.widthPixels;
        laParams.height = displayMetrics.widthPixels;
        game.setLayoutParams(laParams);
        sharedPreferences = getSharedPreferences("小偶像版2048", Context.MODE_PRIVATE);
        Best_Score = sharedPreferences.getInt("Best_Score", 0);
        best_score.setText(Score + "");
        tips.setText("含有隐藏彩蛋，建议在人多的地方，声音开到最大体验，绝对人畜无害！");
        game_2048Activity = this;
        help.setTag(1);
        exit.setTag(2);
        help.setOnClickListener(this);
        exit.setOnClickListener(this);
        hidden_play.setOnCheckedChangeListener(this);
        context = getApplicationContext();
    }

    public static Game_2048Activity getGame_2048Activity() {
        return game_2048Activity;
    }

    public void clearScore(){
        Score = 0;
        showScore();
    }

    public void showScore(){
        score.setText(Score + "");
        best_score.setText(Best_Score + "");
    }

    public void addScore(int s){
        Score += s;
        showScore();
    }

    public int getScore(){
        return Score;
    }

    public void setBest_Score(int best_Score) {
        Best_Score = best_Score;
        showScore();
    }

    @Override
    public void onClick(View view) {
        int tag = (int) view.getTag();
        switch (tag){
            case 1:
                AlertDialog alert = new AlertDialog.Builder(this).create();
                alert.setTitle("玩法");
                alert.setMessage("和2048一样，滑动方块合成新的方块，在2048的基础上增加了隐藏玩法，还有神秘彩蛋等你解锁，快来玩玩看吧");
                alert.show();
                break;
            case 2:
                Intent intent = new Intent(Game_2048Activity.this, MainActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        Toast.makeText(Game_2048Activity.this,"神秘玩法将在正式版开放，先行测试版不支持神秘玩法，敬请期待", Toast.LENGTH_LONG).show();
    }

}
