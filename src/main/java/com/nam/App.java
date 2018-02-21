package com.nam;

import org.newdawn.slick.*;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
/**
 * Hello world!
 *
 */

public class App extends BasicGame
{
    private static Logger log = LoggerFactory.getLogger(App.class);

    private GameLife gl;

    public App(String gamename){
        super(gamename);
    }

    public static void main( String[] args )
    {
        log.info("Pidor");

        try{
            AppGameContainer appgc;
            appgc = new AppGameContainer(new App("Fuck fuker fuck"));
            appgc.setDisplayMode(1024, 768, false);
            appgc.start();
        }
        catch(SlickException ex){
            log.error("Wtf", ex);
        }
    }

    @Override
    public void init(GameContainer gc) throws SlickException{
        //
        gl = new GameLife(0, 0, 1000, 800, 1);

        gl.init();
    }

    @Override
    public void update(GameContainer gc, int i) throws SlickException{
        //

        gl.update();

        if (gc.getInput().isKeyDown(Input.KEY_ESCAPE)){
            gc.setFullscreen(!gc.isFullscreen());
        }
    }

    //@Override
    public void render(GameContainer gc, Graphics g) throws SlickException{
        //
        //g.clear();
        g.setColor(Color.white);
        //g.drawString("Fucker Fuck", 100, 100);

        gl.render(gc, g);

    }
}
