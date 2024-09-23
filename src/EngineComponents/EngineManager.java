package EngineComponents;

import exceptions.FilesIsEmpty;
import io.Gui;
import io.Input;

import java.io.File;
import java.util.Optional;

public class EngineManager {
    SystemSearchEngine searchEngine;

    private String getFileLocation(){
        Gui.fileLocationMessage();
        try {
            FileLocationToString fileLocationToString = new FileLocationToString();
            Optional<String> filePath = fileLocationToString.initiate();

            if(filePath.isPresent()){
                return filePath.get();
            }

        }catch (FilesIsEmpty e){
            System.out.println(e.getMessage());
        }

        return null;
    }

    public void showAllFiles(){
        try{
            String filePath = getFileLocation();

            Gui.searchDepthMessage();
            int depth = Input.readInt();

            new SystemSearchEngine(depth,filePath);
        }catch (FilesIsEmpty e){
            System.out.println(e.getMessage());
        }
    }
}
