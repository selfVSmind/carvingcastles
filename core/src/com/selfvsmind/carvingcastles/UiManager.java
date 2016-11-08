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
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.kotcrab.vis.ui.util.ToastManager;
import com.kotcrab.vis.ui.widget.*;
import com.kotcrab.vis.ui.widget.file.FileChooser;
import com.kotcrab.vis.ui.widget.file.FileTypeFilter;
import com.kotcrab.vis.ui.widget.file.StreamingFileChooserListener;

import static com.kotcrab.vis.ui.widget.file.FileChooser.SelectionMode.DIRECTORIES;
import static com.kotcrab.vis.ui.widget.file.FileChooser.SelectionMode.FILES;

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

    public static void importObj() {
        cleanChooser();

        chooser.setSelectionMode(FILES);
        chooser.setMultiSelectionEnabled(true);
        FileTypeFilter filter = new FileTypeFilter(true);
        filter.addRule("Wavefront OBJ (*.obj)", "obj");
        chooser.setFileTypeFilter(filter);
        chooser.setListener(new StreamingFileChooserListener() {
            @Override
            public void selected (FileHandle file) {
                SceneGraphManager.addNodeToSelected(file);
                toaster.show("Imported " + file.name());
            }
        });

        primaryStage.addActor(chooser.fadeIn());
    }

    public static void doExportDir() {
        cleanChooser();

        chooser.setSelectionMode(DIRECTORIES);
        FileTypeFilter filter = new FileTypeFilter(true);
        filter.addRule("Choose a Directory");
        chooser.setFileTypeFilter(filter);
        primaryStage.addActor(chooser.fadeIn());
    }

    private static void cleanChooser() {
        chooser.setListener(null);
    }
}
