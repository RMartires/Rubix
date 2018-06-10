package com.rgames.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by ROHIT on 3/3/2018.
 */

public class shape extends Actor {
    private Texture texture;
    public Color color;
    private float shapeheight = 100f;
    private float shapewidth = 100f;
    private Sprite shape;
    int sx = 3, sy = 8;
    public boolean hit=false;

    //texture stuff
    private ArrayList<Texture> texturelist = new ArrayList<Texture>();
    private Texture red = new Texture("shapes/redsquare.png");
    private Texture blue = new Texture("shapes/bluesquare.png");
    private Texture yellow = new Texture("shapes/yellowsquare.png");
    private ArrayList<Color> shapeco = new ArrayList<Color>();
    //texture stuff

    public shape(int y) {
        //texture stuff
        texturelist.add(red);
        texturelist.add(blue);
        texturelist.add(yellow);
        //color
        shapeco.add(Color.RED);
        shapeco.add(Color.BLUE);
        shapeco.add(Color.YELLOW);

        Random randomco = new Random();
        int rand = randomco.nextInt(3);
        shape = new Sprite(texturelist.get(rand));
        this.color = shapeco.get(rand);

        //texture stuff ends
        shape.setSize(shapewidth, shapeheight);
        // seting rannd pos

        Random random = new Random();
        int posx = random.nextInt(sx);

        shape.setPosition((float) posx * 100f, (float) y * 100f);
        super.setPosition((float) posx * 100f, (float) y * 100f);
        setBounds(getX(), getY(), shapewidth, shapeheight);
        // end ran pos


    }


    @Override
    public void draw(Batch batch, float parentAlpha) {
        shape.setPosition(this.getX(),this.getY());
        shape.draw(batch);

    }

    public void setRandPosition(float y) {
        // seting rannd pos

        Random random = new Random();
        int posx = random.nextInt(sx);


        shape.setPosition((float) posx * 100f, y);
        // end ran pos
        super.setPosition((float) posx * 100f, y);
        setBounds(getX(), getY(), shapewidth, shapeheight);

    }

    public void translatey(float y) {

        shape.setPosition(shape.getX(), y);
        setBounds(getX(), getY(), shapewidth, shapeheight);

    }

    public void translatex(float x) {
        shape.setPosition(shape.getX() + x, shape.getY());
    }


    @Override
    public float getY() {
        return shape.getY();
    }

    public MoveToAction moveleft() {
        MoveToAction mta = new MoveToAction();
        mta.setPosition(this.getX()-100f, this.getY());
        mta.setDuration(0.3f);

        return mta;
    }

    public MoveToAction moveright() {
        MoveToAction mta = new MoveToAction();
        mta.setPosition(this.getX()+100f, this.getY());
        mta.setDuration(0.3f);

        return mta;
    }

    public void randomco(){
        Random randomco = new Random();
        int rand=randomco.nextInt(3);
        this.shape.setTexture(texturelist.get(rand));
        this.color=shapeco.get(rand);

    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }


}




