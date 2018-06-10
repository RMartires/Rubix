package com.rgames.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;

/**
 * Created by ROHIT on 3/3/2018.
 */

public class background extends Actor {

    Sprite bk = new Sprite(new Texture("background.png"));
    private float width = 300f;
    private float height = 500f;


    public background() {
        setBounds(getX(), getY(), width, height);
        bk.setSize(width, height);

        //touch
/*        addListener(new ActorGestureListener(){
            @Override
            public void fling(InputEvent event, float velocityX, float velocityY, int button) {
                System.out.println(velocityX+"bk");
                super.fling(event, velocityX, velocityY, button);
            }
        });
*/

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        bk.draw(batch);
    }

    @Override
    public void setPosition(float x, float y) {
        super.setPosition(x, y);
        bk.setPosition(x, y);
        setBounds(getX(), getY(), width, height);

    }

}