package com.selfvsmind.carvingcastles;

import java.util.ArrayList;

/**
 * Created by Jason Lambert on 11/8/2016.
 * http://jasonlambert.io
 * jason@jasonlambert.io
 * http://www.github.com/selfVSmind/carvingcastles
 */

public class CCIntermediate3dFormat {
    private String name;
    private ArrayList<CCIntermediate3dFormatMesh> meshes;

    public CCIntermediate3dFormat() {
        this("Anonymous 3D Entity");
    }

    public CCIntermediate3dFormat(String name) {
        this.name = name;

        //assume one mesh per CCIntermediate3dFormat is most common
        meshes = new ArrayList<CCIntermediate3dFormatMesh>(1);
    }
}
