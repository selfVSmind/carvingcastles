/**
 *
 * Created by Jason Lambert on 5/11/2016.
 * http://jasonlambert.io
 * jason@jasonlambert.io
 *
 * http://www.github.com/selfVSmind/carvingcastles
 *
 */

package com.selfvsmind.carvingcastles.widgets;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Tree;
import com.selfvsmind.carvingcastles.ModelResource3d;

public class MyTreeWindowNode extends Tree.Node {
    private ModelResource3d myNode;

    public MyTreeWindowNode(Actor actor, ModelResource3d node) {
        super(actor);
        this.myNode = node;
    }

    public boolean compare(ModelResource3d node) {
        return (this.myNode == node);
    }

}
