package com.nam;

import org.newdawn.slick.*;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import java.util.Random;

public class GameLife {

    private Logger log = LoggerFactory.getLogger(GameLife.class);

    private int mStartX;
    private int mStartY;
    private int mWight;
    private int mHeight;
    private int mSize;

    private Image mImage;
    private Graphics mImageG;

    private int mSpeed = 2;
    private int mTick = 0;

    private ImgBuf mImgBuf;
    private ImgBuf mImgBuf2;
    private int mMaxLive = 300000;

    public GameLife(int startx, int starty, int width, int height, int size){
        log.info("Constructor");

        mStartX = startx;
        mStartY = starty;
        mWight = width;
        mHeight = height;

        mSize = size;

        mImgBuf = new ImgBuf(mWight, mHeight);
        mImgBuf2 = mImgBuf.cp();

    }

    public void init(){
        log.info("Init");

        mImgBuf.doItRand(mMaxLive);

    }

    public void update(){
        if (mTick++ % mSpeed == 0){
            //
            //log.info("Tick Tack");

            mImgBuf2 = mImgBuf.live();
            mImgBuf = mImgBuf2.cp();

        }
    }

    public void render(GameContainer gc, Graphics g){
        //g.setColor(Color.green);

        int x;
        int y;
        int sz = 1;

        for (int i = 0; i < mHeight; i++){
            for (int j = 0; j < mWight; j++){
                x = mStartX + j * mSize;
                y = mStartY + i * mSize;
                if (mImgBuf.getPixel(j, i) == 1){
                    g.fillRect(x, y, mSize, mSize);
                }
            }
        }
    }
}
