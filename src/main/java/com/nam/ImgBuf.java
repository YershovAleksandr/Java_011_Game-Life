package com.nam;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import java.util.Random;

public class ImgBuf {

    private Logger log = LoggerFactory.getLogger(ImgBuf.class);

    private int mWidth;
    private int mHeight;

    private int[][] mPixelMap;

    public ImgBuf(int width, int height){

        mWidth = width;
        mHeight = height;

        mPixelMap = new int[mHeight][mWidth];

        for (int i = 0; i < mHeight; i++){
            for (int j = 0; j < mWidth; j++){
                mPixelMap[i][j] = 0;
            }
        }

    }

    public int getWidth(){
        return mWidth;
    }

    public int getHeight(){
        return mHeight;
    }


    /**
     * printBuf
     *
     */
    public void printBuf(){
        //
        System.out.println("@");
        for (int i = 0; i < mHeight; i++){
            for (int j = 0; j < mWidth; j++){
                if (mPixelMap[i][j] == 1){
                    System.out.print("*");
                }
                else {
                    System.out.print("~");
                }
            }
            System.out.println();
        }
    }

    public void doItRand(int n){
        Random rnd = new Random();

        for (int i = 0; i < n; i++){

            int x;
            int y;

            do{
                x = rnd.nextInt(mWidth);
                y = rnd.nextInt(mHeight);

                if (mPixelMap[y][x] == 0){
                    mPixelMap[y][x] = 1;
                    break;
                }

            } while (mPixelMap[y][x] == 1);
        }
    }

    public ImgBuf cp(){

        ImgBuf ret = new ImgBuf(mWidth, mHeight);

        for (int i = 0; i < mHeight; i++){
            for (int j = 0; j < mWidth; j++){
                int c = mPixelMap[i][j];
                ret.setPixel(j, i, c);
            }
        }

        return ret;
    }

    public ImgBuf live(){
        //
        //log.info("Live");
        ImgBuf ret = new ImgBuf(mWidth, mHeight);

        for (int i = 0; i < mHeight; i++){
            for (int j = 0; j < mWidth; j++){

                int c = mPixelMap[i][j];

                int n = getNeighbor(i , j);
                //Если мёртвая клетка то проверяем соседей.
                //Если 3 живых соседа то клетка становиться живой.
                if (c == 0){
                    if (n == 3){
                        ret.setPixel(j, i, 1);
                    }
                }
                // Если живая клетка то если 2 или 3 соседа живых то живёт дальше если не то умирает.
                else{
                    if (n != 2 && n != 3){
                        ret.setPixel(j, i, 0);
                    }
                    else{
                        ret.setPixel(j, i, 1);
                    }
                }

            }
        }

        return ret;
    }

    /**
     * getNeighbor
     *
     * Check all neighbors.
     * Min 0
     * Max 8
     *
     *
     * @param i
     * @param j
     * @return
     */
    private int getNeighbor(int i , int j){
        int n = 0;
        int ii;
        int jj;

        /*

        * 0 1 2
        * 3 X 4
        * 5 6 7
        *
        * */

        //0
        ii = i - 1;
        jj = j - 1;
        if (ii >= 0 && jj >= 0){
            if (mPixelMap[ii][jj] == 1){
                n++;
            }
        }

        //1
        ii = i - 1;
        jj = j;
        if (ii >= 0){
            if (mPixelMap[ii][jj] == 1){
                n++;
            }
        }

        //2
        ii = i - 1;
        jj = j + 1;
        if (ii >= 0 && jj < mWidth){
            if (mPixelMap[ii][jj] == 1){
                n++;
            }
        }

        //3
        ii = i;
        jj = j - 1;
        if (jj >= 0){
            if (mPixelMap[ii][jj] == 1){
                n++;
            }
        }

        //4
        ii = i;
        jj = j + 1;
        if (jj < mWidth){
            if (mPixelMap[ii][jj] == 1){
                n++;
            }
        }

        //5
        ii = i + 1;
        jj = j - 1;
        if (ii < mHeight && jj >= 0){
            if (mPixelMap[ii][jj] == 1){
                n++;
            }
        }

        //6
        ii = i + 1;
        jj = j;
        if (ii < mHeight){
            if (mPixelMap[ii][jj] == 1){
                n++;
            }
        }

        //7
        ii = i + 1;
        jj = j + 1;
        if (ii < mHeight && jj < mWidth){
            if (mPixelMap[ii][jj] == 1){
                n++;
            }
        }

        return n;
    }

    /**
     * setPixel
     * @param x
     * @param y
     * @param c
     */
    public void setPixel(int x, int y, int c){
        //log.info("SetPixel " + x + "~" + y);
        try{
            mPixelMap[y][x] = c;
        }
        catch (Exception ex){
            log.error("Wtf set", ex);
        }
    }

    /**
     * getPixel
     * @param x
     * @param y
     * @return
     */
    public int getPixel(int x, int y){
        //log.info("GetPixel" + x + "~" + y);

        try{
            return mPixelMap[y][x];
        }
        catch (Exception ex){
            log.error("Wtf get " + x + "~" + y, ex);

            return 42;
        }
    }
}
