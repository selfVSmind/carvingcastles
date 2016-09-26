/**
 *
 * Created by Jason Lambert on 5/17/2016.
 * http://jasonlambert.io
 * jason@jasonlambert.io
 *
 * http://www.github.com/selfVSmind/carvingcastles
 *
 */

package com.selfvsmind.carvingcastles;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.Ray;

public class MyInputProcessor implements InputProcessor {

    private int selected, selecting;
    private Vector3 position;

    MyInputProcessor() {
        selected = selecting = -1;
        position = new Vector3();
    }

    public boolean keyDown (int keycode) {
        return false;
    }

    public boolean keyUp (int keycode) {
        return false;
    }

    public boolean keyTyped (char character) {
        return false;
    }

    public boolean touchDown (int x, int y, int pointer, int button) {
        selecting = getObject(x, y);
        return selecting >= 0;
    }

    public boolean touchUp (int x, int y, int pointer, int button) {
        if (selecting >= 0) {
            if (selecting == getObject(x, y))
//                setSelected(selecting);
            selecting = -1;
            return true;
        }
        return false;
    }

    Vector3 tmpVector = new Vector3();
    public boolean touchDragged (int screenX, int screenY, int pointer) {
        Ray ray = SceneGraphManager.getCam().getPickRay(screenX, screenY);
        final float distance = -ray.origin.y / ray.direction.y;
        tmpVector.set(ray.direction).scl(distance).add(ray.origin);

        ModelInstance instance = SceneGraphManager.getSelectedNode().getModelInstance();
        if (instance == null) return false;

        instance.transform.setTranslation(tmpVector);
        return true;
    }
    public boolean mouseMoved (int x, int y) {
        return false;
    }

    public boolean scrolled (int amount) {
        return false;
    }

    public int getObject(int screenX, int screenY) {
//        Ray pickRay = SceneGraphManager.cam.getPickRay(screenX, screenY);
//        int result = -1;
//        float distance = -1;
//        for (int i = 0; i < SceneGraphManager.getInstances().size; ++i) {
//            final ModelInstance instance = SceneGraphManager.getInstances().get(i);
//            instance.transform.getTranslation(position);
//            position.add(instance.center);
//        }
        return 934287;
    }
}