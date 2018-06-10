package com.rgames.game;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

/**
 * Created by ROHIT on 4/14/2018.
 */

public class Buttton extends TextButton {
    public boolean hit=false;
    public String name;

    public Buttton(String text, Skin skin, String styleName) {
        super(text, skin, styleName);
        this.name=text;

    }







}
