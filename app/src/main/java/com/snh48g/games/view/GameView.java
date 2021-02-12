package com.snh48g.games.view;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.media.MediaPlayer;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.GridLayout;

import androidx.core.content.ContextCompat;

import com.snh48g.games.Game_2048Activity;
import com.snh48g.games.R;
import com.snh48g.games.entity.Card;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameView extends GridLayout {

    MediaPlayer mediaPlayer;
    int music_id;
    int[] music = {R.raw.fangqi_sound, R.raw.sushanshan_sound1, R.raw.sushanshan_sound2};
    private Card[][] cardMap = new Card[4][4];
    private List<Point> emptyPoints = new ArrayList<>();

    public GameView(Context context) {
        super(context);
        initGameView();
    }

    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initGameView();
    }

    public GameView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initGameView();
    }

    public GameView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initGameView();
    }

    private void initGameView() {
        setColumnCount(4);
        setBackgroundColor(ContextCompat.getColor(getContext(), R.color.game_bg));
        setOnTouchListener(new OnTouchListener() {
            private float startX;
            private float startY;
            private float endX, endY;
            private float offsetX, offsetY;

            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        startX = motionEvent.getX();
                        startY = motionEvent.getY();
                        break;
                    case MotionEvent.ACTION_UP:
                        endX = motionEvent.getX();
                        endY = motionEvent.getY();
                        offsetX = endX - startX;
                        offsetY = endY - startY;
                        if (Math.abs(offsetX) > Math.abs(offsetY)) {
                            if (offsetX < -5) {
                                MoveLeft();

                            } else if (offsetX > 5) {
                                MoveRight();
                            }
                        } else {
                            if (offsetY < -5) {
                                MoveUp();
                            } else if (offsetY > 5) {
                                MoveDown();
                            }
                        }
                        break;
                }
                return true;
            }
        });
    }

    private void MoveUp() {
        boolean move = false;
        for (int j = 0; j < 4; j++) {
            for (int i = 0; i < 4; i++) {
                for (int x = i + 1; x < 4; x++) {
                    if (cardMap[x][j].getNumber() > 0) {
                        if (cardMap[i][j].getNumber() <= 0) {
                            cardMap[i][j].setNumber(cardMap[x][j].getNumber());
                            cardMap[x][j].setNumber(0);
                            x = i + 1;
                            move = true;
                        } else if (cardMap[i][j].equals(cardMap[x][j])) {
                            cardMap[i][j].setNumber(cardMap[i][j].getNumber() * 2);
                            cardMap[x][j].setNumber(0);
                            Game_2048Activity.getGame_2048Activity().addScore(cardMap[i][j].getNumber());
                            UpdateBest_Score();
                            move = true;
                        }
                    }
                }
            }
        }
        if (move) {
            addRandomNumber();
            Music();
            checkGameOver();
        }
    }

    private void MoveDown() {
        boolean move = false;
        for (int j = 0; j < 4; j++) {
            for (int i = 3; i >= 0; i--) {
                for (int x = i - 1; x >= 0; x--) {
                    if (cardMap[x][j].getNumber() > 0) {
                        if (cardMap[i][j].getNumber() <= 0) {
                            cardMap[i][j].setNumber(cardMap[x][j].getNumber());
                            cardMap[x][j].setNumber(0);
                            x = i - 1;
                            move = true;
                        } else if (cardMap[i][j].equals(cardMap[x][j])) {
                            cardMap[i][j].setNumber(cardMap[i][j].getNumber() * 2);
                            cardMap[x][j].setNumber(0);
                            Game_2048Activity.getGame_2048Activity().addScore(cardMap[i][j].getNumber());
                            UpdateBest_Score();
                            move = true;
                        }
                    }
                }
            }
        }
        if (move) {
            addRandomNumber();
            Music();
            checkGameOver();
        }
    }

    private void MoveLeft() {
        boolean move = false;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                for (int y = j + 1; y < 4; y++) {
                    if (cardMap[i][y].getNumber() > 0) {
                        if (cardMap[i][j].getNumber() <= 0) {
                            cardMap[i][j].setNumber(cardMap[i][y].getNumber());
                            cardMap[i][y].setNumber(0);
                            y = j + 1;
                            move = true;
                        } else if (cardMap[i][j].equals(cardMap[i][y])) {
                            cardMap[i][j].setNumber(cardMap[i][j].getNumber() * 2);
                            cardMap[i][y].setNumber(0);
                            Game_2048Activity.getGame_2048Activity().addScore(cardMap[i][j].getNumber());
                            UpdateBest_Score();
                            move = true;
                        }
                    }
                }
            }
        }
        if (move) {
            addRandomNumber();
            Music();
            checkGameOver();
        }
    }

    private void MoveRight() {
        boolean move = false;
        for (int i = 0; i < 4; i++) {
            for (int j = 3; j >= 0; j--) {
                for (int y = j - 1; y >= 0; y--) {
                    if (cardMap[i][y].getNumber() > 0) {
                        if (cardMap[i][j].getNumber() <= 0) {
                            cardMap[i][j].setNumber(cardMap[i][y].getNumber());
                            cardMap[i][y].setNumber(0);
                            y = j - 1;
                            move = true;
                        } else if (cardMap[i][j].equals(cardMap[i][y])) {
                            cardMap[i][j].setNumber(cardMap[i][j].getNumber() * 2);
                            cardMap[i][y].setNumber(0);
                            Game_2048Activity.getGame_2048Activity().addScore(cardMap[i][j].getNumber());
                            UpdateBest_Score();
                            move = true;
                        }
                    }
                }
            }
        }
        if (move) {
            addRandomNumber();
            Music();
            checkGameOver();
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        int cardSize = (Math.min(w, h) - 20) / 4;
        addCards(cardSize);
        startGame();
    }

    private void addCards(int cardSize) {
        Card card;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                card = new Card(getContext());
                card.setNumber(0);
                addView(card, cardSize, cardSize);
                cardMap[i][j] = card;
            }
        }
    }

    private void addRandomNumber() {
        emptyPoints.clear();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (cardMap[i][j].getNumber() <= 0) {
                    emptyPoints.add(new Point(i, j));
                }
            }
        }
        Point point = emptyPoints.remove((int) (Math.random() * emptyPoints.size()));
        cardMap[point.x][point.y].setNumber(Math.random() > 0.1 ? 2 : 4);
    }

    public void startGame() {
        Game_2048Activity.getGame_2048Activity().clearScore();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                cardMap[i][j].setNumber(0);
            }
        }
        addRandomNumber();
        addRandomNumber();
    }

    private void checkGameOver() {
        boolean complete = true;
        ALL:
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (cardMap[i][j].getNumber() <= 0 ||
                        (i > 0 && cardMap[i][j].equals(cardMap[i - 1][j])) ||
                        (i < 3 && cardMap[i][j].equals(cardMap[i + 1][j])) ||
                        (j > 0 && cardMap[i][j].equals(cardMap[i][j - 1])) ||
                        (j < 3 && cardMap[i][j].equals(cardMap[i][j + 1]))) {
                    complete = false;
                    break ALL;
                }
            }
        }
        if (complete) {
            new AlertDialog.Builder(getContext()).setTitle("小偶像版2048").setMessage("游戏结束！").setPositiveButton("重新开始", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    startGame();
                }
            }).show();
        }
    }

    public void UpdateBest_Score() {
        int best_score, score;
        SharedPreferences sharedPreferences;
        sharedPreferences = getContext().getSharedPreferences("小偶像版2048", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        score = Game_2048Activity.getGame_2048Activity().getScore();
        best_score = sharedPreferences.getInt("Best_Score", 0);
        if (best_score < score) {
            editor.putInt("Best_Score", score);
            Game_2048Activity.getGame_2048Activity().setBest_Score(score);
            editor.apply();
        }
    }

    public void Music(){
        Random rand = new Random();
        int num = rand.nextInt(3);
        music_id = music[num];
        mediaPlayer = MediaPlayer.create(getContext(), music_id);
        if(mediaPlayer != null){
            mediaPlayer.start();
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener(){

                @Override
                public void onCompletion(MediaPlayer mp) {
                    mediaPlayer.stop();
                }
            });
        }
    }

}
