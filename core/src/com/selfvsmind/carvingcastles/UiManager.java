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
        //this is the bar at the top of screen with things like 'file' and 'help'
        menuBar = new MyMenuBar();

        //the menubar won't fill up the width of the window by itself
        //so here we embed it in a table and set that table to be drawn at the top of the window
        table = new VisTable();
        table.setFillParent(true);
        table.add(menuBar.getTable()).fillX().expandX().colspan(Gdx.graphics.getWidth()).row();
        table.top();
        primaryStage.addActor(table);

        //here we're setting up the vertical row of buttons of the left side
        //later they will actually do things like rotate, scale, that sort of thing
        table.row();
        table.add(new MyVerticalButtonColumn());

        sceneGraphTreeWindow = new MyTreeWindow();
        primaryStage.addActor(sceneGraphTreeWindow);
    }

    public static void render() {
        //rendering for 2D user interface starts here
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
        //clear out the previous fileListener
        cleanChooser();

        //select directories only
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
