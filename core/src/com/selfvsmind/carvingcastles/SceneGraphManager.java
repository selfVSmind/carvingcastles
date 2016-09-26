/**
 *
 * Created by Jason Lambert on 5/11/2016.
 * http://jasonlambert.io
 * jason@jasonlambert.io
 *
 * http://www.github.com/selfVSmind/carvingcastles
 *
 */

package com.selfvsmind.carvingcastles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController;
import com.badlogic.gdx.scenes.scene2d.ui.Tree;
import com.badlogic.gdx.scenes.scene2d.utils.Selection;
import com.badlogic.gdx.utils.Array;
import com.kotcrab.vis.ui.widget.VisTree;
import com.kotcrab.vis.ui.widget.file.StreamingFileChooserListener;

import java.io.FileFilter;

public final class SceneGraphManager {

    private static AssetManager manager;
    private static VisTree uiTree;
    private static MyTreeWindowNode uiTreeRootNode;
    private static ModelResource3d rootNode;
    private static Environment environment;
    private static ModelBatch modelBatch;
    private static Model model;
    private static ModelInstance modelInstance;
    private static PerspectiveCamera cam;
    private static CameraInputController camController;
    private static Array<ModelInstance> instances = new Array<ModelInstance>();

    public static VisTree init() {
        manager = new AssetManager();
        uiTree = new VisTree();
        rootNode = new ModelResource3d("root", null, null);
        uiTreeRootNode = rootNode.getTreeNode();
        uiTree.add(uiTreeRootNode);

        environment = new Environment();
        environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.4f, 0.4f, 0.4f, 1f));
        environment.add(new DirectionalLight().set(0.8f, 0.8f, 0.8f, -1f, -0.8f, -0.2f));

        modelBatch = new ModelBatch();

        cam = new PerspectiveCamera(67, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        cam.position.set(1f, 1f, 1f);
        cam.lookAt(0,0,0);
        cam.near = 1f;
        cam.far = 300f;
        cam.update();

        camController = new CameraInputController(cam);

        loadDummyDefaults();

        return uiTree;
    }

    private static void loadDummyDefaults() {
//        addNodeToSelected(new FileHandle("data/spacesphere.obj"));
//        clearSelection();
//        addNodeToSelected(new FileHandle("data/ship.obj"));
    }

    public static ModelResource3d getSelectedNode() {
        ModelResource3d node;
        Selection<Tree.Node> selection = uiTree.getSelection();
        if (selection.hasItems()) {
            node = rootNode.searchForNode((MyTreeWindowNode)selection.first());
            if (node == null) throw new NullPointerException("Can't find the correct node?!!? What have you done wrong?");
        }
        else node = rootNode;
        return node;
    }

    private static void updateSelection(Tree.Node treeNode) {
        Selection<Tree.Node> selection = uiTree.getSelection();
        selection.clear();
        selection.set(treeNode);
    }

    private static void clearSelection() {
        Selection<Tree.Node> selection = uiTree.getSelection();
        selection.clear();
    }

    private static ModelResource3d addNodeToSelected(FileHandle file) {
        ModelResource3d selectedNode = getSelectedNode();

        manager.load(file.path(), Model.class);
        manager.finishLoading();
        model = manager.get(file.path(), Model.class);
        modelInstance = new ModelInstance(model);
        instances.add(modelInstance);
        ModelResource3d newNode = new ModelResource3d(file.nameWithoutExtension(), file.path(), modelInstance);

        selectedNode.addChildNode(newNode);
        if (selectedNode == rootNode) {
            uiTreeRootNode.add(newNode.getTreeNode());
            uiTreeRootNode.setExpanded(true);
        }
        else {
            MyTreeWindowNode treeNode = selectedNode.getTreeNode();
            treeNode.add(newNode.getTreeNode());
            treeNode.setExpanded(true);
        }

        updateSelection(newNode.getTreeNode());
        return newNode;
    }

    public static void n64ButtonClicked() {
        UiManager.chooser.setMultiSelectionEnabled(true);
        UiManager.chooser.setFileFilter(new FileFilter() {
            @Override
            public boolean accept(java.io.File pathname) {
                return (pathname.getPath().endsWith(".obj") || pathname.isDirectory());
            }
        });
        UiManager.chooser.setListener(new StreamingFileChooserListener() {
            @Override
            public void selected (FileHandle file) {
                addNodeToSelected(file);
                UiManager.toaster.show("Imported " + file.name());
            }
        });

        UiManager.primaryStage.addActor(UiManager.chooser.fadeIn());
    }

    public static void render() {
        camController.update();

        modelBatch.begin(cam);
        modelBatch.render(instances, environment);
        modelBatch.end();
    }

    public static void dispose() {
        modelBatch.dispose();
        instances.clear();
        manager.dispose();
    }

    public static Camera getCam() {
        return cam;
    }
}
