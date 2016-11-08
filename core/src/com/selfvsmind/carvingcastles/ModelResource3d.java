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

import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.math.Matrix4;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.selfvsmind.carvingcastles.widgets.MyTreeWindowNode;

import java.util.ArrayList;

public class ModelResource3d {
    private String fileName;
    private String objectName;
    private Matrix4 localTransform;
    private Matrix4 worldTransform;
    private ModelInstance modelInstance;
    private ArrayList<ModelResource3d> children;
    private MyTreeWindowNode treeNode;

    public ModelResource3d(String objectName, String fileName, ModelInstance instance) {
        this.fileName = fileName;
        this.objectName = objectName;
        localTransform = new Matrix4();
        worldTransform = new Matrix4();
        this.modelInstance = instance;
        children = new ArrayList<ModelResource3d>();
        treeNode = new MyTreeWindowNode(new VisLabel(objectName), this);
    }

    public void addChildNode(ModelResource3d node) {
        children.add(node);
    }

    public Matrix4 getLocalTransform() {
        //update localTransform
        return localTransform;
    }

    public Matrix4 getWorldTransform() {
        //update worldTransform
        return worldTransform;
    }

    public String getFileName() throws NullPointerException {
        if (fileName == null) throw new NullPointerException("This ModelResource3d has no FileName associated with it.");
        return fileName;
    }

    public String getObjectName() {
        return objectName;
    }

    public ModelInstance getModelInstance() {
        return modelInstance;
    }

    public MyTreeWindowNode getTreeNode() {
        return treeNode;
    }

    public ModelResource3d searchForNode(MyTreeWindowNode treeNode) {
        if (treeNode.compare(this)) return this;
        return recursiveSearch(treeNode);
    }

    private ModelResource3d recursiveSearch(MyTreeWindowNode treeNode) {
        ModelResource3d found;
        for (ModelResource3d child:children
             ) {
            found = child.searchForNode(treeNode);
            if(found != null) return found;
        }
        return null;
    }
}
