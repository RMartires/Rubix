package com.rgames.game.states;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.rgames.game.Rubix;
import com.rgames.game.background;
import com.rgames.game.shape;


import java.awt.event.ActionListener;
import java.util.ArrayList;

import static com.badlogic.gdx.Input.Keys.G;
import static com.badlogic.gdx.Input.Keys.S;

/**
 * Created by ROHIT on 3/4/2018.
 */

public class play implements GestureDetector.GestureListener,Screen{

    shape cube;
    OrthographicCamera cam;
    Stage stage;
    FitViewport fitViewport;

    ArrayList<background> bklist;
    ArrayList<shape> shapelist;

    SpriteBatch batch;

    Game game;

    //counters
    double i=0;
    double SV=0;


    public play(Game agame) {
        this.game=agame;

        //initialize
        batch = new SpriteBatch();
        shapelist = new ArrayList<shape>();
        bklist = new ArrayList<background>();

        stage = new Stage();
        cam = new OrthographicCamera(300f,400f);
        fitViewport = new FitViewport(300f,400f,cam);

        //end ini
        for(int b=0;b<1;b++) {
            bklist.add(new background());
        }

        stage.addActor(bklist.get(0));

        for(int i=0;i<8;i++){
            shapelist.add(new shape(i+3));
            stage.addActor(shapelist.get(i));
        }

        stage.setViewport(fitViewport);
        fitViewport.apply();
        cam.translate(cam.viewportWidth/2,cam.viewportHeight/2);

//start touch

        for(Actor ac:stage.getActors()){
            ac.setTouchable(Touchable.enabled);
        }

// end touch

    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(new GestureDetector(this));
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        //update stuff
        //shape move and repo
        for(int s=0;s<8;s++){

            if((shapelist.get(s).getY()) <= (cam.position.y - 200f)) {

                //check if its proper
                if(shapelist.get(s).color==Color.RED&&shapelist.get(s).getX()!=0f)
                    gameover();
                if(shapelist.get(s).color==Color.BLUE&&shapelist.get(s).getX()!=100f)
                    gameover();
                if(shapelist.get(s).color==Color.YELLOW&&shapelist.get(s).getX()!=200f)
                    gameover();
                //end check

                shapelist.get(s).setRandPosition((800f+shapelist.get(s).getY()));
                shapelist.get(s).randomco();
                stage.addActor(shapelist.get(s));

            }

            i+=0.0005f;
            if(i>0&&i<3.240)
                SV=1;
            if(i>3.240&&i<6.240)
                SV=1.5;
            if(i>6.2400&&i<14.8080)
                SV=1.8;
            if(i>14.8080&&i<29.616)
                SV=2.5;
            if(i>29.616&&i<44.424)
                SV=2.9;
            if(i>44.424&&i<59.232)
                SV=3.3;




            shapelist.get(s).translatey((float) (shapelist.get(s).getY()-SV));

            System.out.println(i);



        }

        //update stuff ends


        batch.setProjectionMatrix(cam.combined);
        batch.begin();
        stage.act();
        stage.draw();
        batch.end();

    }

    @Override
    public void resize(int width, int height) {
        fitViewport.update(width,height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        stage.dispose();
        batch.dispose();
    }

    public void gameover(){
        game.setScreen(new endgame(game));
    }



//touch gesture stuff

    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {

        Vector2 vector12 = new Vector2(Gdx.input.getX(),Gdx.input.getY());
        Vector3 touch = cam.unproject(new Vector3(vector12.x,vector12.y,0));

        for(shape s:shapelist){
            Vector2 shapeco = s.localToStageCoordinates(new Vector2(s.getX(),s.getY()));

            if(shapeco.x/2<=touch.x&&shapeco.x/2+100>=touch.x&&shapeco.y/2<=touch.y&&shapeco.y/2+100>=touch.y) {
                s.hit = true;
                System.out.println("hit");
            }


        }

        return true;
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {
        return false;
    }

    @Override
    public boolean longPress(float x, float y) {
        return false;
    }

    @Override
    public boolean fling(float velocityX, float velocityY, int button) {

        for(shape s:shapelist){
        if(velocityX<0&&s.hit==true&&s.getX()>0f)
            s.addAction(s.moveleft());
        if(velocityX>0&&s.hit==true&&s.getX()<200f)
            s.addAction(s.moveright());


        s.hit=false;
        }


        return true;
    }

    @Override
    public boolean pan(float x, float y, float deltaX, float deltaY) {
        return false;
    }

    @Override
    public boolean panStop(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean zoom(float initialDistance, float distance) {
        return false;
    }

    @Override
    public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
        return false;
    }

    @Override
    public void pinchStop() {

    }
}
