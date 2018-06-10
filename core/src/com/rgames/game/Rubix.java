package com.rgames.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.rgames.game.states.endgame;
import com.rgames.game.states.startgame;

public class Rubix extends Game{


    @Override
	public void create () {
    	this.setScreen(new startgame(this));

    }

	@Override
	public void render () {
    	super.render();
	}
	
	@Override
	public void dispose () {
    
	}


}
