package ru.ncedu.dmdrozhzhin.fileexplorer;

import java.io.File;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class FileExplorerTest {
    private String path = "forTest\\findTest";
    FileExplorer fileExplorer = new FileExplorer();
    File directory;

    @org.junit.jupiter.api.Test
    void findFile() {

        List<String> stringList = fileExplorer.findFile(path, "new\\.*");
        for (String test : stringList) {
            assertEquals(test.contains("new"), true);
        }

        List<String> allFilesList = fileExplorer.findFile(path, "");
        directory = new File(path);
        String[] allFiles = directory.list();
        assertEquals(allFilesList.size(), allFiles.length);
    }

    @org.junit.jupiter.api.Test
    void moveFiles() {
        String fileSourcePath = "forTest\\moveTest\\files";
        String fileDistPath = "forTest\\moveTest\\toMove";

        File sourceDir = new File(fileSourcePath);
        File distDir = new File(fileDistPath);

        int SDirCountFileB = sourceDir.list().length;
        int DDirCountFileB = distDir.list().length;

        fileExplorer.moveFiles(fileSourcePath, fileDistPath);

        int SDirCountFileA = sourceDir.list().length;
        int DDirCountFileA = distDir.list().length;

        assertNotEquals(SDirCountFileB, SDirCountFileA);
        assertEquals(DDirCountFileA, DDirCountFileB + SDirCountFileB);
        assertEquals(SDirCountFileA, 0);

        fileExplorer.moveFiles(fileDistPath, fileSourcePath);
    }

    @org.junit.jupiter.api.Test
    void copyFiles() {
        String fileSourcePath = "forTest\\copyTest\\files";
        String fileDistPath = "forTest\\copyTest\\toCopy";

        File sourceDir = new File(fileSourcePath);
        File distDir = new File(fileDistPath);

        int SDirCountFileB = sourceDir.list().length;
        int DDirCountFileB = distDir.list().length;

        fileExplorer.copyFiles(fileSourcePath, fileDistPath);

        int SDirCountFileA = sourceDir.list().length;
        int DDirCountFileA = distDir.list().length;

        assertEquals(SDirCountFileB, SDirCountFileA);
        assertEquals(DDirCountFileA, DDirCountFileB + SDirCountFileB);

        for(File file : distDir.listFiles() ){
            file.delete();
        }
    }
}