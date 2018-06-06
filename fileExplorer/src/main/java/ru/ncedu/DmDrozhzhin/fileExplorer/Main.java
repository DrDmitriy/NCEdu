package ru.ncedu.DmDrozhzhin.fileExplorer;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        //  FileExplorer.printAttribute(FileExplorer.getPath(""));
        // FileExplorer.printStructure(Paths.get("D:\\IdeaProject\\JavaCode.git\\NCEdu\\fileExplorer"));
        /*
        List<String> stringList = fileExplorer.findFile(Paths.get("forTest"), "");
        for (String a : stringList) {
            System.out.println(a);
        }*/
        FileExplorer fileExplorer = new FileExplorer();
        String fileSourcePath = "forTest\\moveTest\\files";
        String fileDistPath =   "forTest\\moveTest\\toMove";

       // fileExplorer.findFile("forTest\\findTest","new\\.*");
        //fileExplorer.findFile("forTest\\findTest","");
        fileExplorer.moveFiles("forTest\\moveTest\\files","C:\\test");

       // fileExplorer.moveFiles(fileSourcePath,fileDistPath);
       // fileExplorer.moveFiles(fileDistPath,fileSourcePath);


       //test();
        String a2 = "D:\\test\\tt.txt";
        String b2 = "D:\\test\\new";
        String b3 = "D:\\test\\rang1\\new";
        String b4 = "D:\\test\\level";
        //FileExplorer fileExplorer = new FileExplorer();
        //fileExplorer.fi
        //fileExplorer.moveFiles(a2,b2);
        //fileExplorer.moveFiles(a2,b2);
        //fileExplorer.copyFiles(b3,b4);
       // File file = new File("forTest\\newone.txt");
       // System.out.println(file.exists());
    }

    static void test(){
        Path a = Paths.get("D:\\test\\level1");
        Path b = Paths.get("test");
        Path newP = a.resolve(b); // Прибавляет к пути а, строку б
        System.out.println(newP.toString());

        Path a2 = Paths.get("C:\\test");
        Path b2 = Paths.get("D:\\test\\level1");


        System.out.println(a2.relativize(b2)); // Возвращает разницу между 1 и 2 ссылкой

    }


}
