/**
 *
 * Created by Jason Lambert on 5/14/2016.
 * http://jasonlambert.io
 * jason@jasonlambert.io
 *
 * http://www.github.com/selfVSmind/carvingcastles
 *
 */

package com.selfvsmind.carvingcastles;

import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.kotcrab.vis.ui.layout.HorizontalFlowGroup;

public class MyVerticalButtonColumn extends HorizontalFlowGroup {

    MyVerticalButtonColumn() {
        super();
        
//        this.setFillParent(true);
        this.addActor(new ImageButton(UiManager.uiSkin.get("castle", ImageButton.ImageButtonStyle.class)).pad(4));
        this.addActor(new ImageButton(UiManager.uiSkin.get("3floppy", ImageButton.ImageButtonStyle.class)).pad(4));
        this.addActor(new ImageButton(UiManager.uiSkin.get("exec", ImageButton.ImageButtonStyle.class)).pad(4));
        this.addActor(new ImageButton(UiManager.uiSkin.get("cdrom", ImageButton.ImageButtonStyle.class)).pad(4));
        this.addActor(new ImageButton(UiManager.uiSkin.get("source_py", ImageButton.ImageButtonStyle.class)).pad(4));
        this.addActor(new ImageButton(UiManager.uiSkin.get("source_h", ImageButton.ImageButtonStyle.class)).pad(4));
        this.addActor(new ImageButton(UiManager.uiSkin.get("colorscm", ImageButton.ImageButtonStyle.class)).pad(4));
        this.addActor(new ImageButton(UiManager.uiSkin.get("castle", ImageButton.ImageButtonStyle.class)).pad(4));
        this.addActor(new ImageButton(UiManager.uiSkin.get("font", ImageButton.ImageButtonStyle.class)).pad(4));
        this.addActor(new ImageButton(UiManager.uiSkin.get("castle", ImageButton.ImageButtonStyle.class)).pad(4));
        this.addActor(new ImageButton(UiManager.uiSkin.get("txt", ImageButton.ImageButtonStyle.class)).pad(4));
        this.addActor(new ImageButton(UiManager.uiSkin.get("document", ImageButton.ImageButtonStyle.class)).pad(4));
    }
}
