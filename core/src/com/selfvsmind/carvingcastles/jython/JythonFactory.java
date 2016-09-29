/**
 *
 * Created by Jason Lambert on 9-27-2016.
 * http://jasonlambert.io
 * jason@jasonlambert.io
 *
 * http://www.github.com/selfVSmind/carvingcastles
 *
 */

package com.selfvsmind.carvingcastles.jython;

import org.python.core.PyObject;
import org.python.util.PythonInterpreter;
import org.python.core.PySystemState;
import org.python.core.PyString;

import java.io.File;


public class JythonFactory {

    private PyObject ExampleClass;

    public JythonFactory(String modulename, String classname) {
        PythonInterpreter interpreter = new PythonInterpreter(null, new PySystemState());
        PySystemState sys = interpreter.getSystemState();
////        String workingPath = System.getProperty("user.dir") + File.separator + "python";
//        String workingPath = PyObject.class.getProtectionDomain().getCodeSource().getLocation().getPath();
//        workingPath = "jar:file:" + workingPath + "!/python";
//        System.out.println(workingPath);
        sys.path.append(new PyString("python"));
        interpreter.exec("from " + modulename + " import " + classname);
        ExampleClass = interpreter.get(classname);
    }

    public TestJythonType create () {
        PyObject ExampleObject = ExampleClass.__call__();
        return (TestJythonType) ExampleObject.__tojava__(TestJythonType.class);
    }
}
