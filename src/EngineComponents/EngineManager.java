package EngineComponents;

import exceptions.FilesIsEmpty;
import io.Gui;
import io.Input;

import java.io.File;
import java.util.Optional;

public class EngineManager {
    SystemSearchEngine searchEngine;

    public Optional<File> fileExplorer(){
        Gui.fileLocationMessage();
        try {
            FileExplorer fileLocationToString = new FileExplorer();
            Optional<File> file = fileLocationToString.initiate();

            if(file.isPresent()){
                return file;
            }else {
                throw new FilesIsEmpty();
            }

        }catch (FilesIsEmpty e){
            System.out.println(e.getMessage());
        }catch (NullPointerException e){
            System.out.println("Error");
        }

        return Optional.empty();
    }

    public void showAllFiles(){
        try{
            Optional<File> file = fileExplorer();
            if(file.isPresent()){
                Gui.searchDepthMessage();
                int depth = Input.readInt();

                new SystemSearchEngine(depth,file.get().getAbsolutePath());
            }
        }catch (FilesIsEmpty e){
            System.out.println(e.getMessage());
        }
    }
}
