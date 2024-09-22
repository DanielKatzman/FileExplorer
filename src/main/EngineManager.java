package main;

import exceptions.FilesIsEmpty;

import java.io.File;

public class EngineManager {
    SystemSearchEngine searchEngine;

    public void showAllFiles(int depth){
        try{
            String filePath = SystemSearchEngine.fileLocationToString(new File("C:\\"));
            new SystemSearchEngine(depth,filePath);
        }catch (FilesIsEmpty e){
            System.out.println(e.getMessage());
        }
    }
}
