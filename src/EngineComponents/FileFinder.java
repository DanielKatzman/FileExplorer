package EngineComponents;

import java.io.File;
import java.util.Optional;

public class FileFinder {
    private static String fileName;
    //todo finish making the class!!!
    public Optional<File> initiate(String name) {
        fileName = name;
        File[] files = File.listRoots();
        Optional<File> isFound = Optional.empty();
        for (File file : files) {
            isFound = systemSearch(file);
        }
        return isFound;

    }

    private Optional<File> systemSearch(File currentDirectory){
        File[] files = currentDirectory.listFiles();

        if(files == null) return Optional.empty();

        for(File file : files){
            if(checkFileName(fileName, file)){
                return Optional.of(file);
            }
            return systemSearch(file);
        }
        return Optional.empty();
    }

    private boolean checkFileName(String name, File file){
        return file.getName().equals(name);
    }
}
