package EngineComponents;

import exceptions.FilesExceptions;
import exceptions.FilesIsEmpty;
import exceptions.NoAccess;
import io.Gui;
import io.Input;

import java.io.File;
import java.util.Optional;

public class EngineManager {

    public Optional<File> fileExplorer(){
        Gui.fileLocationMessage();
        FileExplorer_2_0 fileExplorer = new FileExplorer_2_0();

        return fileExplorer.initiate();
    }

    public void showAllFiles(){
        Optional<File> file = fileExplorer();
        if(file.isPresent()){
            File output = file.get();
            System.out.println("Enter search depth");
            int depth = Input.readInt();
            ListAllFiles_2_0 allFiles = new ListAllFiles_2_0(depth, output.getAbsoluteFile());
            allFiles.initiate();
        }else System.out.println("no file selected!");

    }

}
