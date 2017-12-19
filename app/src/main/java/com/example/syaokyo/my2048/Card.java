package com.example.syaokyo.my2048;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.TextView;

/**
 * Created by SyaoKyo on 2017/12/18.
 */

public class Card extends FrameLayout {
    private int num;
    private TextView label;

    public Card(@NonNull Context context) {
        super(context);
        initCard();
    }

    public Card(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initCard();
    }

    public Card(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initCard();
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
        if (num<=0)
            label.setText("");
        else
            label.setText(num + "");
        color(num);
    }
    public boolean equalCard(Card c){
        return this.getNum()==c.getNum();
    }

    private void initCard(){
        label = new TextView(getContext());
        label.setTextSize(30);

        LayoutParams layoutParams = new LayoutParams(-1, -1);
        layoutParams.setMargins(10,10,0,0);
        label.setBackgroundColor(0x33ffffff);
        label.setGravity(Gravity.CENTER);
        addView(label, layoutParams);
        setNum(0);
    }
    private void color(int num){
        switch (num){
            case 0:
                label.setBackgroundColor(0x33ffffff);
                break;
            case 2:
                label.setBackgroundColor(0xead1dcff);
                break;
            case 4:
                label.setBackgroundColor(0xd5a6bdff);
                break;
            case 8:
                label.setBackgroundColor(0xc27ba0ff);
                break;
            case 16:
                label.setBackgroundColor(0xb4a7d6ff);
                break;
            case 32:
                label.setBackgroundColor(0x8e7cc3ff);
                break;
            case 64:
                label.setBackgroundColor(0x674ea7ff);
                break;
            case 128:
                label.setBackgroundColor(0x3d85c6ff);
                break;
            case 256:
                label.setBackgroundColor(0x3c78d8ff);
                break;
            case 512:
                label.setBackgroundColor(0x45818eff);
                break;
            case 1024:
                label.setBackgroundColor(0x6aa84fff);
                break;
            case 2048:
                label.setBackgroundColor(0xf1c232ff);
                break;
            default:
                label.setBackgroundColor(0xff0000ff);
        }
    }
}
