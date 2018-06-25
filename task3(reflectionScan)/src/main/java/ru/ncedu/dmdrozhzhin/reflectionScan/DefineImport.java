package ru.ncedu.dmdrozhzhin.reflectionScan;

import com.thoughtworks.qdox.JavaProjectBuilder;
import com.thoughtworks.qdox.model.JavaSource;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * uses external java library Qdox for parsing source java files
 */
public class DefineImport {
    /**
     * @param sourcePath path to java class
     * @return ArrayList<String    > with full names of imported classes
     */
    public static List<String> getImportsSet(String sourcePath) {
        JavaProjectBuilder projectBuilder = new JavaProjectBuilder();
        try {
            projectBuilder.addSource(new FileReader(sourcePath));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Collection<JavaSource> src = projectBuilder.getSources();
        Iterator iterator = src.iterator();
        List list = ((JavaSource) iterator.next()).getImports();

        return list;
    }
}
