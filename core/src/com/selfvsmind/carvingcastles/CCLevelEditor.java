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

//        InputMultiplexer multiplexer = new InputMultiplexer();
//        multiplexer.addProcessor(UiManager.primaryStage);
////        multiplexer.addProcessor(SceneGraphManager.camController);
//        multiplexer.addProcessor(new MyInputProcessor());
//        Gdx.input.setInputProcessor(new InputMultiplexer(UiManager.primaryStage, new MyInputProcessor()));
        Gdx.input.setInputProcessor(new InputMultiplexer(UiManager.primaryStage, new MyInputProcessor()));
    }

    @Override
    public void render() {

        Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

        SceneGraphManager.render();
        UiManager.render();
    }

    @Override
    public void dispose() {
        SceneGraphManager.dispose();
        UiManager.dispose();

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
