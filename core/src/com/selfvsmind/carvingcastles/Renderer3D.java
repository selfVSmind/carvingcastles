package com.selfvsmind.carvingcastles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController;

/**
 * Created by Jason Lambert on 11/8/2016.
 * http://jasonlambert.io
 * jason@jasonlambert.io
 * http://www.github.com/selfVSmind/carvingcastles
 */

//This class is designed to be instanced only ONCE
public class Renderer3D {
    private static Environment environment;
    private static PerspectiveCamera cam;
    private static CameraInputController camController;

    public static void init() {
        environment = new Environment();
        environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.4f, 0.4f, 0.4f, 1f));
        environment.add(new DirectionalLight().set(0.8f, 0.8f, 0.8f, -1f, -0.8f, -0.2f));

        cam = new PerspectiveCamera(67, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        cam.position.set(1f, 1f, 1f);
        cam.lookAt(0,0,0);
        cam.near = 1f;
        cam.far = 300f;
        cam.update();

        camController = new CameraInputController(cam);
    }
    public static void render() {

        //the setup
        Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
        camController.update();

        //the rendering
        renderSceneGraph();
    }

    private static void renderSceneGraph() {
        ModelBatch modelBatch = SceneGraphManager.getModelBatch();
        modelBatch.begin(cam);
        modelBatch.render(SceneGraphManager.getInstances(), environment);
        modelBatch.end();
    }

    public static Camera getCam() {
        return cam;
    }
}
