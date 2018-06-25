package ru.ncedu.dmdrozhzhin.fileexplorer;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileExplorer {
    public List<String> findFile(String stringPath, String regEx) {
        Path path = Paths.get(stringPath);
        Set<Path> setPath = new HashSet<>();
        List<String> stringList = new ArrayList<>();

        MyVisitorFindFile myVisitorFindFile = new MyVisitorFindFile(path);
        try {
            myVisitorFindFile.clearSet();
            Files.walkFileTree(path, myVisitorFindFile);
        } catch (NullPointerException e) {

            System.out.println("Укажите путь к папке");
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        }

        Set<Path> setPathFiles = myVisitorFindFile.getPathSet();
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher;
        for (Path p : setPathFiles) {

            matcher = pattern.matcher(p.toString());
            if (matcher.find()) {
                setPath.add(p);
            }
        }

        if (setPath.isEmpty()) {
            System.out.println("File not found");
        } else {
            for (Path a : setPath) {
                stringList.add(a.getFileName().toString());
            }
        }
        return stringList;
    }

    public void copyFiles(String soursePath, String destinationPath) {
        if (soursePath == null|| soursePath.equals("")||destinationPath == null || destinationPath.equals("")) {
            System.out.println("Укажите корректный путь для метода copyFile");
            return;
        }
        Path source = Paths.get(soursePath);
        Path destination = Paths.get(destinationPath);

        if (!new File(soursePath).isDirectory()) {
            try {
                destination = Paths.get(destination.toAbsolutePath().toString() + "\\" + source.getFileName());
                Files.copy(source, destination, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }
        try {
            Files.walkFileTree(source, new MyVisitorCopyFile(source, destination));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void moveFiles(String soursePath, String destinationPath) {
        if (soursePath == null || soursePath.equals("") || destinationPath == null || destinationPath.equals("")) {
            System.out.println("Укажите корректный путь для метода moveFile");
            return;
        }
        Path source = Paths.get(soursePath);
        Path destination = Paths.get(destinationPath);
        if (!new File(soursePath).isDirectory()) {
            try {
                destination = Paths.get(destination.toAbsolutePath().toString() + "\\" + source.getFileName());
                Files.move(source, destination, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }
        try {
            Files.walkFileTree(source, new MyVisitorMoveFile(source, destination));
            File oldDerection = new File(source.toAbsolutePath().toString());
            if (!oldDerection.exists()) {
                oldDerection.mkdir();
            }
        } catch (NoSuchFileException e) {
            e.printStackTrace();
            File oldDerection = new File(source.toAbsolutePath().toString());
            if (!oldDerection.exists()) {
                oldDerection.mkdir();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private class MyVisitorCopyFile extends SimpleFileVisitor<Path> {
        private Path sourcePath, destPath;

        private MyVisitorCopyFile(Path sourcePath, Path destPath) {
            this.destPath = destPath;
            this.sourcePath = sourcePath;

        }

        public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) throws IOException {
            Path newPath = destPath.resolve(sourcePath.relativize(path));
            Files.copy(path, newPath, StandardCopyOption.REPLACE_EXISTING);
            return FileVisitResult.CONTINUE;
        }


        public FileVisitResult preVisitDirectory(Path path, BasicFileAttributes attrs) throws IOException {
            Path newPath = destPath.resolve(sourcePath.relativize(path));
            File newDerection = new File(newPath.toAbsolutePath().toString());
            if (!newDerection.exists()) {
                newDerection.mkdir();
            }
            return FileVisitResult.CONTINUE;
        }
    }

    private class MyVisitorMoveFile extends SimpleFileVisitor<Path> {
        private Path sourcePath, destPath;

        private MyVisitorMoveFile(Path sourcePath, Path destPath) {
            this.destPath = destPath;
            this.sourcePath = sourcePath;
        }

        @Override
        public FileVisitResult preVisitDirectory(Path path, BasicFileAttributes attrs) throws IOException {
            Path newPath = destPath.resolve(sourcePath.relativize(path));
            File newDerection = new File(newPath.toAbsolutePath().toString());
            if (!newDerection.exists()) {
                newDerection.mkdir();
            }
            return FileVisitResult.CONTINUE;
        }

        @Override
        public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
            new File(dir.toAbsolutePath().toString()).delete();
            return FileVisitResult.CONTINUE;
        }

        @Override
        public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) throws IOException {
            Path newPath = destPath.resolve(sourcePath.relativize(path));
            try {
                Files.move(path, newPath, StandardCopyOption.REPLACE_EXISTING);
            } catch (NoSuchFileException e) {
                e.printStackTrace();
            }
            return FileVisitResult.CONTINUE;
        }
    }

    private class MyVisitorFindFile extends SimpleFileVisitor<Path> {
        private Path sourcePath;
        private Set<Path> fileNameSet = new HashSet<>();

        public MyVisitorFindFile(Path sourcePath) {
            this.sourcePath = sourcePath;
        }

        public Set<Path> getPathSet() {
            return fileNameSet;
        }

        public void clearSet() {
            fileNameSet.clear();
        }

        public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) {
            if (!(path.getParent().toString().equals(sourcePath.toString()))) {
                return FileVisitResult.CONTINUE;
            }
            fileNameSet.add(path);
            return FileVisitResult.CONTINUE;
        }

        public FileVisitResult preVisitDirectory(Path path, BasicFileAttributes fileAttributes) {
            if (path.toString().equals(sourcePath.toString())) {
                return FileVisitResult.CONTINUE;
            }
            fileNameSet.add(path);
            return FileVisitResult.CONTINUE;
        }
    }
}
