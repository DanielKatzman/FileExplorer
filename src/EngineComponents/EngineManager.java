package EngineComponents;

import io.FileWriterToText;
import io.Gui;
import io.Input;
import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.util.Optional;

public class EngineManager {

    public Optional<File> fileExplorer(){
        Gui.fileLocationMessage();
        FileExplorer_2_0 fileExplorer = new FileExplorer_2_0();
        try {
            return fileExplorer.initiate();
        }catch (IOException e){
            e.printStackTrace();
        }

        return Optional.empty();
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

    public void writeToText(){
        try {
            Optional<File> file = fileExplorer();
            if(file.isPresent()){
                File output = file.get();
                System.out.println("Enter search depth");
                int depth = Input.readInt();
                FileWriterToText ft = new FileWriterToText();
                ListAllFilesToText allFiles = new ListAllFilesToText(depth, output.getAbsoluteFile());
                allFiles.initialise();
                FileWriterToText.writerClose();
            }else System.out.println("no file selected!");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
