/**
 *
 * Created by Jason Lambert on 5/5/2016.
 * http://jasonlambert.io
 * jason@jasonlambert.io
 *
 * http://www.github.com/selfVSmind/carvingcastles
 *
 */

package com.selfvsmind.carvingcastles.widgets;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.kotcrab.vis.ui.widget.Menu;
import com.kotcrab.vis.ui.widget.MenuBar;
import com.kotcrab.vis.ui.widget.MenuItem;
import com.kotcrab.vis.ui.widget.PopupMenu;
import com.kotcrab.vis.ui.widget.file.FileTypeFilter;
import com.selfvsmind.carvingcastles.UiManager;

import static com.kotcrab.vis.ui.widget.file.FileChooser.SelectionMode.DIRECTORIES;
import static com.kotcrab.vis.ui.widget.file.FileChooser.SelectionMode.FILES;

public class MyMenuBar extends MenuBar {

    private static Menu menu;

    public MyMenuBar () {
        super();

        menu = new Menu("File");
        MenuItem importObj = new MenuItem("Import OBJ");
        importObj.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeListener.ChangeEvent event, Actor actor) {
                UiManager.importObj();
            }
        });

        menu.addItem(importObj);
        MenuItem export = new MenuItem("Export");

        PopupMenu exportSubMenu = new PopupMenu();
        exportSubMenu.addItem(new MenuItem("Custom Export Format"));
        exportSubMenu.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeListener.ChangeEvent event, Actor actor) {
                UiManager.doExportDir();
            }
        });

        export.setSubMenu(exportSubMenu);
        menu.addItem(export);
        menu.addSeparator();
        menu.addItem(new MenuItem("Save").setShortcut("CTRL+S"));
        this.addMenu(menu);

        Menu menu2 = new Menu("Edit");
        menu2.addItem(new MenuItem("Preferences"));
        this.addMenu(menu2);
    }
}
