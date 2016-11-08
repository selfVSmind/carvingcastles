/**
 *
 * Created by Jason Lambert.
 * http://jasonlambert.io
 * jason@jasonlambert.io
 *
 * http://www.github.com/selfVSmind/carvingcastles
 *
 */

package com.selfvsmind.carvingcastles;

import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

import com.badlogic.gdx.graphics.g3d.Renderable;
import com.kotcrab.vis.ui.VisUI;
//import com.kotcrab.vis.ui.util.ToastManager;
import com.kotcrab.vis.ui.widget.file.FileChooser;

public class CCLevelEditor implements ApplicationListener {

    @Override
    public void create() {
        VisUI.load();
//        VisUI.load(VisUI.SkinScale.X2);
        FileChooser.setFavoritesPrefsName("com.selfvsmind.carvingcastles");

        UiManager.init();
        Renderer3D.init();

//        InputMultiplexer multiplexer = new InputMultiplexer();
//        multiplexer.addProcessor(UiManager.primaryStage);
////        multiplexer.addProcessor(SceneGraphManager.camController);
//        multiplexer.addProcessor(new MyInputProcessor());
//        Gdx.input.setInputProcessor(new InputMultiplexer(UiManager.primaryStage, new MyInputProcessor()));
        Gdx.input.setInputProcessor(new InputMultiplexer(UiManager.primaryStage, new MyInputProcessor()));
    }

    @Override
    public void render() {
        //first draw the 3D world
        Renderer3D.render();
        //then draw the 2D UI overlay
        UiManager.render();
    }

    @Override
    public void dispose() {
        //My stuff
        SceneGraphManager.dispose();
        UiManager.dispose();

        //External Libs
        VisUI.dispose();
    }

    @Override
    public void resize(int width, int height) {
//        cam.update();
        UiManager.primaryStage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }
}
