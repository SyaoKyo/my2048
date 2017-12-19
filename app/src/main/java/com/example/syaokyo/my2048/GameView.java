package com.example.syaokyo.my2048;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.GridLayout;

import java.util.ArrayList;
import java.util.List;



/**
 * Created by SyaoKyo on 2017/12/18.
 */

public class GameView extends GridLayout {
    private Card[][] cardMap = new Card[4][4];
    private List<Point> emptyPoints= new ArrayList<>();
    private boolean merge=false;

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

    private void initGameView() {
        setColumnCount(4);
        setBackgroundColor(0xffbbbbb0);
//        背景图
//        setBackgroundResource(R.drawable.back);
        setOnTouchListener(new OnTouchListener() {
            private float startX, startY, offsetX, offsetY;
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        startX = event.getX();
                        startY = event.getY();
                        break;
                    case MotionEvent.ACTION_UP:
                        offsetX = event.getX() - startX;
                        offsetY = event.getY() - startY;
                        if (Math.abs(offsetX) > Math.abs(offsetY)) {
                            if (offsetX < -5) {
                                swipeLeft();
                            } else if (offsetX > 5) {
                                swipeRight();
                            }
                        } else {
                            if (offsetY < -5) {
                                swipeUp();
                            } else if (offsetY > 5) {
                                swipeDown();
                            }
                        }
                        break;
                }
                return true;
            }
        });
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        int cardWidth = (Math.min(w, h) - 10) / 4;
        addCard(cardWidth);
        startGame();
    }

    private void addCard(int cardWidth) {
        Card card;
        for (int x = 0; x < 4; x++) {
            for (int y= 0; y < 4; y++) {
                card = new Card(getContext());
                card.setNum(0);
                addView(card, cardWidth, cardWidth);
                cardMap[x][y] = card;
            }
        }
    }

    private void addRandomNum(){
        emptyPoints.clear();
        for (int y=0;y<4;y++)
            for (int x=0;x<4;x++)
                if (cardMap[x][y].getNum()==0)
                    emptyPoints.add(new Point(x,y));
        if(emptyPoints.size()>0){
            Point point=emptyPoints.remove((int)(Math.random()*emptyPoints.size()));
            int num;
            if (Math.random()>0.1){
                num=2;
            }else{
                num=4;
            }
            cardMap[point.x][point.y].setNum(num);
        }
    }

    public void startGame(){
        MainActivity.getMainActivity().clearScore();
        for (int y=0;y<4;y++)
            for (int x=0;x<4;x++)
                cardMap[x][y].setNum(0);
        addRandomNum();
        addRandomNum();
    }

    private void swipeLeft() {
        merge=false;
        for(int x=0;x<4;x++){
            for(int y=0;y<4;y++){
                for(int y1=y+1;y1<4;y1++){
                    if(cardMap[x][y1].getNum()>0){
                        if(cardMap[x][y].getNum()<=0){
                            cardMap[x][y].setNum(cardMap[x][y1].getNum());
                            cardMap[x][y1].setNum(0);
                            y--;
                            merge=true;
                        }else if(cardMap[x][y].equalCard(cardMap[x][y1])){
                            cardMap[x][y].setNum(cardMap[x][y].getNum()*2);
                            cardMap[x][y1].setNum(0);
                            merge=true;
                            score(cardMap[x][y].getNum());
                        }
                        break;
                    }
                }
            }
        }
        if (merge){
            addRandomNum();
            checkOver();
        }
    }

    private void swipeDown() {
        merge=false;
        for (int y=0;y<4;y++){
            for (int x=3;x>=0;x--){
                for (int x1=x-1;x1>=0;x1--){
                    if (cardMap[x1][y].getNum()>0){
                        if (cardMap[x][y].getNum()<=0){
                            cardMap[x][y].setNum(cardMap[x1][y].getNum());
                            cardMap[x1][y].setNum(0);
                            x++;
                            merge=true;
                        }else if (cardMap[x][y].equalCard(cardMap[x1][y])){
                            cardMap[x][y].setNum(cardMap[x][y].getNum()*2);
                            cardMap[x1][y].setNum(0);
                            merge=true;
                            score(cardMap[x][y].getNum());
                        }
                        break;
                    }
                }
            }
        }
        if (merge){
            addRandomNum();
            checkOver();
        }
    }

    private void swipeUp() {
        merge=false;
        for (int y=0;y<4;y++){
            for (int x=0;x<4;x++){
                for (int x1=x+1;x1<4;x1++){
                    if (cardMap[x1][y].getNum()>0){
                        if (cardMap[x][y].getNum()<=0){
                            cardMap[x][y].setNum(cardMap[x1][y].getNum());
                            cardMap[x1][y].setNum(0);
                            x--;
                            merge=true;
                        }else if (cardMap[x][y].equalCard(cardMap[x1][y])){
                            cardMap[x][y].setNum(cardMap[x][y].getNum()*2);
                            cardMap[x1][y].setNum(0);
                            merge=true;
                            score(cardMap[x][y].getNum());
                        }
                        break;
                    }
                }
            }
        }
        if (merge){
            addRandomNum();
            checkOver();
        }
    }

    private void swipeRight() {
        merge=false;
        for(int x=0;x<4;x++){
            for(int y=3;y>=0;y--){
                for(int y1=y-1;y1>=0;y1--){
                    if(cardMap[x][y1].getNum()>0){
                        if(cardMap[x][y].getNum()<=0){
                            cardMap[x][y].setNum(cardMap[x][y1].getNum());
                            cardMap[x][y1].setNum(0);
                            y++;
                            merge=true;
                        }else if(cardMap[x][y].equalCard(cardMap[x][y1])){
                            cardMap[x][y].setNum(cardMap[x][y].getNum()*2);
                            cardMap[x][y1].setNum(0);
                            merge=true;
                            score(cardMap[x][y].getNum());
                        }
                        break;
                    }
                }
            }
        }
        if (merge){
            addRandomNum();
            checkOver();
        }
    }

    private void checkOver() {
        boolean over=true;
        ALL:
        for(int y=0;y<4;y++) {
            for (int x = 0; x < 4; x++) {
                if(cardMap[x][y].getNum()==0||
                        (x>0&&cardMap[x][y].equalCard(cardMap[x-1][y]))||
                        (x<3&&cardMap[x][y].equalCard(cardMap[x+1][y]))||
                        (y>0&&cardMap[x][y].equalCard(cardMap[x][y-1]))||
                        (y<3&&cardMap[x][y].equalCard(cardMap[x][y+1]))){
                    over=false;
                    break ALL;
                }

            }
        }
        if(over){
            new AlertDialog.Builder(getContext()).setTitle("你好！")
                    .setMessage("游戏结束！")
                    .setPositiveButton("重新开始", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            startGame();
                        }
                    }).show();
        }
    }

    private void score(int s){
        MainActivity.getMainActivity().addScore(s);
    }
}
