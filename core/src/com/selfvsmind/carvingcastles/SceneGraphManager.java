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
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g3d.*;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.model.MeshPart;
import com.badlogic.gdx.graphics.g3d.model.Node;
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController;
import com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.scenes.scene2d.ui.Tree;
import com.badlogic.gdx.scenes.scene2d.utils.Selection;
import com.badlogic.gdx.utils.Array;
import com.kotcrab.vis.ui.widget.VisTree;
import com.kotcrab.vis.ui.widget.file.FileTypeFilter;
import com.kotcrab.vis.ui.widget.file.StreamingFileChooserListener;
import com.sun.glass.ui.SystemClipboard;

import java.io.FileFilter;

import static com.kotcrab.vis.ui.widget.file.FileChooser.SelectionMode.FILES;

public final class SceneGraphManager {

    private static AssetManager manager;
    private static VisTree uiTree;
    private static MyTreeWindowNode uiTreeRootNode;
    private static ModelResource3d rootNode;
    private static ModelBatch modelBatch;
    private static Model model;
    private static ModelInstance modelInstance;
    private static Array<ModelInstance> instances = new Array<ModelInstance>();

    public static VisTree init() {
        manager = new AssetManager();
        uiTree = new VisTree();
        rootNode = new ModelResource3d("root", null, null);
        uiTreeRootNode = rootNode.getTreeNode();
        uiTree.add(uiTreeRootNode);

        modelBatch = new ModelBatch();

        loadDummyDefaults();

        MeshPart part = instances.get(0).model.meshParts.get(0);

        float[] vertices = new float[part.mesh.getNumVertices() * 3];
        short[] indices = new short[part.mesh.getNumIndices()];
        part.mesh.getVertices(vertices);
        part.mesh.getIndices(indices);

        //print out the vertices
        for(int i = 0; i < vertices.length; i += 3) {
            System.out.print(vertices[i] + "f, " + vertices[i+1] + "f, " + vertices[i+2] + "f, \n");
//            System.out.print(vertices[i+3] + "f, " + vertices[i+4] + "f, " + vertices[i+5] + "f, \n");
//            System.out.print(vertices[i+6] + "f, " + vertices[i+7] + "f, \n\n");
        }

        //print out the indices
        System.out.println();
        for(int i = 0; i < indices.length; i++) {
            System.out.print(indices[i] + ", ");
        }

//        System.out.println(vertices.length);
//        System.out.println(indices.length);

        return uiTree;
    }

    private static void loadDummyDefaults() {
//        addNodeToSelected(new FileHandle("c:/testdata/cube.obj"));
//        clearSelection();
//        addNodeToSelected(new FileHandle("c:/testdata/ship.obj"));
//        clearSelection();
        addNodeToSelected("triangle");
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

    public static ModelResource3d addNodeToSelected(String name) {
        ModelResource3d selectedNode = getSelectedNode();


        Mesh mesh = new Mesh(true, 3, 3, new VertexAttribute(VertexAttributes.Usage.Position, 3, "a_position"));

        mesh.setVertices(new float[] {
                -0.5f, -0.5f, 0,    //bottom-left
                0.5f, -0.5f, 0,    //bottom-right
                0, 0.5f, 0 });     //top
        mesh.setIndices(new short[] { 0, 1, 2 });


        ModelBuilder modelBuilder = new ModelBuilder();
        modelBuilder.begin();
        MeshPartBuilder meshBuilder;
        meshBuilder = modelBuilder.part("part1", GL20.GL_TRIANGLES, VertexAttributes.Usage.Position, new Material());
        meshBuilder.addMesh(mesh);
        Node node = modelBuilder.node();
        node.translation.set(10,0,0);
        meshBuilder = modelBuilder.part("part2", GL20.GL_TRIANGLES, VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal, new Material());
        meshBuilder.sphere(5, 5, 5, 10, 10);
        Model model = modelBuilder.end();


        modelInstance = new ModelInstance(model);
        instances.add(modelInstance);
        ModelResource3d newNode = new ModelResource3d(name, name, modelInstance);

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

    public static ModelResource3d addNodeToSelected(FileHandle file) {
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

    public static ModelBatch getModelBatch() {
        return modelBatch;
    }

    public static Array<ModelInstance> getInstances() {
        return instances;
    }

    public static void dispose() {
        modelBatch.dispose();
        instances.clear();
        manager.dispose();
    }
}
