package ru.ncedu.DmDrozhzhin.fileExplorer;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MyFileVisitor extends SimpleFileVisitor<Path> {
    private Path sourcePath, destPath;

    public MyFileVisitor(Path sourcePath) {

        this.sourcePath = sourcePath;

    }

    private Set<Path> fileNameSet = new HashSet<>();

    /*public Set<String> getFileNameSet() {
        return fileNameSet;
    }*/

    public Set<Path> getPathSet()  {
        return fileNameSet;
    }

    public void clearSet(){
        fileNameSet.clear();
    }

    public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) throws IOException {
       if (!(path.getParent().toString().equals(sourcePath.toString()))) {
          // System.out.println(" sourcePath parent is  " + sourcePath.toString() +"  path parent is " + path.getParent().toString());
           return FileVisitResult.CONTINUE;
       }



        path = path.normalize();

        fileNameSet.add(path);
        return FileVisitResult.CONTINUE;
    }

    public FileVisitResult preVisitDirectory(Path path, BasicFileAttributes fileAttributes) throws IOException {
        if(path.toString().equals(sourcePath.toString())) {
            return FileVisitResult.CONTINUE;
        }
        fileNameSet.add(path);
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {

        return FileVisitResult.CONTINUE;
    }
}
