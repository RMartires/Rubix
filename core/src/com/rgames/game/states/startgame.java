package com.rgames.game.states;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.rgames.game.Buttton;
import com.rgames.game.background;
import com.rgames.game.states.play;

import java.util.ArrayList;

/**
 * Created by ROHIT on 4/15/2018.
 */

public class startgame implements Screen,GestureDetector.GestureListener {

    private Skin skin;
    private Label rubix;
    private Stage stage;
    private OrthographicCamera cam;
    private FitViewport viewport;
    private ArrayList<Buttton> buttonlist = new ArrayList<Buttton>();
    final Game game;
    private SpriteBatch batch;



    public startgame(Game agame) {
        this.game=agame;

        stage = new Stage();
        batch=new SpriteBatch();
        background bg= new background();
        cam = new OrthographicCamera(300f,400f);
        viewport = new FitViewport(300f,400f,cam);
        stage.setViewport(viewport);
        cam.translate(cam.viewportWidth/2,cam.viewportHeight/2);
        viewport.apply();

        skin = new Skin(Gdx.files.internal("data/freezing-ui.json"));
        //button stuff||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||

        Buttton start = new Buttton("Start",skin,"default");
        start.setPosition(80f,cam.viewportHeight/2-start.getHeight()/2+50f);
        start.setSize(140f,50f);
        buttonlist.add(start);

        Buttton setting = new Buttton("Setting",skin,"default");
        setting.setPosition(80f,cam.viewportHeight/2-setting.getHeight()/2-20f);
        setting.setSize(140f,50f);
        buttonlist.add(setting);

        Buttton share = new Buttton("Share",skin,"default");
        share.setPosition(80f,cam.viewportHeight/2-share.getHeight()/2-90f);
        share.setSize(140f,50f);
        buttonlist.add(share);

        rubix = new Label("Rubix",skin,"default");
        rubix.setPosition(40f,cam.viewportHeight/2-start.getHeight()/2+170f);
        rubix.setFontScale(5f,5f);


        //end button stuff||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
        stage.addActor(bg);
        stage.addActor(start);
        stage.addActor(setting);
        stage.addActor(share);
        stage.addActor(rubix);


        for(Actor ac:stage.getActors()){

            ac.setTouchable(Touchable.enabled);
        }


    }





    @Override
    public void show() {
        Gdx.input.setInputProcessor(new GestureDetector(this));
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.setProjectionMatrix(cam.combined);
        batch.begin();
        stage.draw();
        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width,height);
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


//touch stuff
    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {

        Vector2 vector12 = new Vector2(Gdx.input.getX(),Gdx.input.getY());
        Vector3 touch = cam.unproject(new Vector3(vector12.x,vector12.y,0));
        for(Buttton B:buttonlist){
            Vector2 shapeco = B.localToStageCoordinates(new Vector2(B.getX(),B.getY()));

            if(shapeco.x/2<=touch.x-30f&&shapeco.x/2+110f>=touch.x&&shapeco.y/2<=touch.y&&shapeco.y/2+50f>=touch.y){
                if(B.name=="Start") {
                    game.setScreen(new play(game));

                }


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
        return false;
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
