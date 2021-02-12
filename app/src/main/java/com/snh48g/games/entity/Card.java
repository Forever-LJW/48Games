package com.snh48g.games.entity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.media.MediaPlayer;
import android.provider.MediaStore;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.snh48g.games.Game_2048Activity;
import com.snh48g.games.R;

import java.util.Random;

public class Card extends FrameLayout {

    int number, img_id;
    TextView show_num;

    public int getImg(){
            if(getNumber() == 2){
                img_id = R.drawable.dumpling;
            }
            if(getNumber() == 4){
                img_id = R.drawable.liangqiao;
            }
            if(getNumber() == 8){
                img_id = R.drawable.liangjiao;
            }
            if(getNumber() == 16){
                img_id = R.drawable.huangyici;
            }
            if(getNumber() == 32){
                img_id = R.drawable.huangxuanqi;
            }
            if(getNumber() == 64){
                img_id = R.drawable.zhoushiyu;
            }
            if(getNumber() == 128){
                img_id = R.drawable.wangyi;
            }
            if(getNumber() == 256){
                img_id = R.drawable.fangqi;
            }
            if(getNumber() == 512){
                img_id = R.drawable.sushanshan;
            }
            if(getNumber() == 1024){
                img_id = R.drawable.duanyixuan;
            }
            if(getNumber() == 2048){
                img_id = R.drawable.zhanghuaijing;
            }
        return img_id;
    }

    public int getNumber() {
        return number;
    }

    @SuppressLint("SetTextI18n")
    public void setNumber(int number) {
        this.number = number;
        if (number > 0) {
            show_num.setTextColor(ContextCompat.getColor(getContext(), R.color.text));
            show_num.setBackgroundResource(getImg());
        } else {
            show_num.setTextColor(ContextCompat.getColor(getContext(), R.color.text));
            show_num.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.card_bg));
        }
        switch (number) {
            case 0:
                show_num.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.card_bg));
                break;
            case 2:
                show_num.setTextColor(ContextCompat.getColor(getContext(), R.color.text));
                show_num.setBackgroundResource(getImg());
                break;
            case 4:
                show_num.setTextColor(ContextCompat.getColor(getContext(), R.color.text));
                show_num.setBackgroundResource(getImg());
                break;
            case 16:
                show_num.setTextColor(ContextCompat.getColor(getContext(), R.color.text));
                show_num.setBackgroundResource(getImg());
                break;
            case 32:
                show_num.setTextColor(ContextCompat.getColor(getContext(), R.color.text));
                show_num.setBackgroundResource(getImg());
                break;
            case 64:
                show_num.setTextColor(ContextCompat.getColor(getContext(), R.color.text));
                show_num.setBackgroundResource(getImg());
                break;
            case 128:
                show_num.setTextColor(ContextCompat.getColor(getContext(), R.color.text));
                show_num.setBackgroundResource(getImg());
                break;
            case 256:
                show_num.setTextColor(ContextCompat.getColor(getContext(), R.color.text));
                show_num.setBackgroundResource(getImg());
                break;
            case 512:
                show_num.setTextColor(ContextCompat.getColor(getContext(), R.color.text));
                show_num.setBackgroundResource(getImg());
                break;
            case 1024:
                show_num.setTextColor(ContextCompat.getColor(getContext(), R.color.text));
                show_num.setBackgroundResource(getImg());
                break;
            case 2048:
                show_num.setTextColor(ContextCompat.getColor(getContext(), R.color.text));
                show_num.setBackgroundResource(getImg());
                break;
            default:
                break;
        }
    }

    public Card(Context context){
        super(context);
        show_num = new TextView(getContext());
        show_num.setTextSize(32);
        show_num.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.card_bg));
        show_num.setGravity(Gravity.CENTER);
        LayoutParams layoutParams = new LayoutParams(-1, -1);
        layoutParams.setMargins(10,10,0,0);
        addView(show_num, layoutParams);
        setNumber(0);
    }

    public boolean equals(Card card){
        return getNumber() == card.getNumber();
    }

}