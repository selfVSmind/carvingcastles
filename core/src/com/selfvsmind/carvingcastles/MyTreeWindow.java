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

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.kotcrab.vis.ui.VisUI;
import com.kotcrab.vis.ui.util.TableUtils;
import com.kotcrab.vis.ui.widget.VisTree;
import com.kotcrab.vis.ui.widget.VisWindow;

public class MyTreeWindow extends VisWindow {

    public MyTreeWindow() {
        super("Scene Graph");
        this.setResizable(true);

        TableUtils.setSpacingDefaults(this);
        columnDefaults(0).left();

        VisTree tree = SceneGraphManager.init();

        ScrollPane scrollPane = new ScrollPane(tree, VisUI.getSkin(), "list");
        add(scrollPane).expand().fill();

        setSize(250, 380);
        setPosition(75, 423);

        ImageButton castleButton = new ImageButton(UiManager.uiSkin.get("castle", ImageButton.ImageButtonStyle.class));
        castleButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
            SceneGraphManager.castleButtonClicked();
            }
        });
        this.add(castleButton.pad(4));
    }
}
