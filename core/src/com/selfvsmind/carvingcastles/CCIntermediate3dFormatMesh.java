package com.selfvsmind.carvingcastles;

/**
 * Created by Jason Lambert on 11/8/2016.
 * http://jasonlambert.io
 * jason@jasonlambert.io
 * http://www.github.com/selfVSmind/carvingcastles
 */

public class CCIntermediate3dFormatMesh {
    private String name;
    private float[] vertices, normals, uvs;
    private int[] indices;

    private boolean verticesSet, normalsSet, uvsSet, indicesSet;

    public CCIntermediate3dFormatMesh() {
        this("Anonymous 3D Mesh");
    }

    public CCIntermediate3dFormatMesh(String name) {
        this.name = name;
        verticesSet = false;
        normalsSet = false;
        uvsSet = false;
        indicesSet = false;
    }

    public void setVertices(float[] vertices) {
        copyFloatArray(vertices, this.vertices);
        verticesSet = true;
    }

    public void setNormals(float[] normals) {
        copyFloatArray(normals, this.normals);
        normalsSet = true;
    }

    public void setUvs(float[] uvs) {
        copyFloatArray(uvs, this.uvs);
        uvsSet = true;
    }

    public void setIndices(int[] indices) {
        copyIntArray(indices, this.indices);
        indicesSet = true;
    }

    private void copyFloatArray(float[] source, float[] destination) {
        for(int i = 0; i < source.length - 1; ++i) {
            destination[i] = source[i];
        }
    }

    private void copyIntArray(int[] source, int[] destination) {
        for(int i = 0; i < source.length - 1; ++i) {
            destination[i] = source[i];
        }
    }
}
