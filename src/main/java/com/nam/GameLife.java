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

    private Image mImage;
    private Graphics mImageG;

    public GameLife(int startx, int starty, int width, int height){
        log.info("Constructor");

        mStartX = startx;
        mStartY = starty;
        mWight = width;
        mHeight = height;
    }

    public void init(){
        log.info("Init");

        try{
            mImage = new Image(mWight, mHeight);
            mImageG = mImage.getGraphics();
        }
        catch (SlickException ex){
            log.error("Wtf", ex);
        }

        mImageG.setBackground(Color.green);
        mImageG.clear();

        ImageBuffer ib = new ImageBuffer(mWight, mHeight);

        Random rnd = new Random();

        for (int i = 0; i < mHeight; i++){
            for (int j = 0; j < mWight; j++){
                int r = rnd.nextInt(0xFF);
                int g = rnd.nextInt(0xFF);
                int b = rnd.nextInt(0xFF);
                int a = rnd.nextInt(0xFF);
                ib.setRGBA(j, i, r, g, b, a);
            }
        }

        mImage = ib.getImage();

        mImageG.setColor(Color.blue);
        mImageG.drawRect(10, 10, 20, 20);

        mImageG.flush();

    }

    public void render(GameContainer gc, Graphics g){
        //
        g.drawImage(mImage, mStartX, mStartY);

        g.setColor(Color.red);
        g.drawRect(mStartX, mStartY, mWight, mHeight);



    }

}
