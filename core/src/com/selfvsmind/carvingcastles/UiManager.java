/**
 *
 * Created by Jason Lambert on 5/5/2016.
 * http://jasonlambert.io
 * jason@jasonlambert.io
 *
 * http://www.github.com/selfVSmind/carvingcastles
 *
 */

package com.selfvsmind.carvingcastles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.kotcrab.vis.ui.util.ToastManager;
import com.kotcrab.vis.ui.widget.*;
import com.kotcrab.vis.ui.widget.file.FileChooser;

public final class UiManager {

    private static VisTable table;

    public static Skin uiSkin;
    public static Stage primaryStage;
    public static MenuBar menuBar;
    public static MyTreeWindow sceneGraphTreeWindow;
    public static ToastManager toaster;

    final public static FileChooser chooser = new FileChooser(FileChooser.Mode.OPEN);

    public static void init() {
        uiSkin = new Skin(Gdx.files.internal("skin/uiskin.json"));
        primaryStage = new Stage();
        toaster = new ToastManager(primaryStage);
        getEverythingSetUp();
    }

    private static void getEverythingSetUp() {
        menuBar = new MyMenuBar();

        table = new VisTable();
        table.setFillParent(true);
//        table.add(menuBar.getTable()).fillX().expandX().row();
        table.add(menuBar.getTable()).fillX().expandX().colspan(Gdx.graphics.getWidth()).row();
//        table.add().expandX().fill();
        table.top();
        primaryStage.addActor(table);

        table.row();
        table.add(new MyVerticalButtonColumn());

        sceneGraphTreeWindow = new MyTreeWindow();
        primaryStage.addActor(sceneGraphTreeWindow);
    }

    public static void render() {
        primaryStage.act(Gdx.graphics.getDeltaTime());
        primaryStage.draw();
    }

    public static void dispose() {
        primaryStage.dispose();
    }
}
