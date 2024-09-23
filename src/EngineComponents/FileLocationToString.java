package EngineComponents;

import exceptions.FilesIsEmpty;
import io.Gui;
import io.Input;

import java.io.File;
import java.util.Optional;

public class FileLocationToString {
    public Optional<String> initiate() throws FilesIsEmpty {
        return fileLocation(new File("C:\\"));
    }


    private Optional<String> fileLocation(File file)throws FilesIsEmpty {
        File[] files = file.listFiles(File::isDirectory);
        if(files == null){
            throw new FilesIsEmpty();
        }

        Gui.printDirectoryDetails(files);
        Gui.chooseFileFromList();
        int selectedFile = Input.readInt();
        Gui.selectedDirectoyMessage(files[selectedFile]);

        Gui.fileLocationQuestion();
        int fileOptions = Input.readInt();

        switch (fileOptions){
            case 1:
                return Optional.of(files[selectedFile].getAbsolutePath());

            case 2:
                fileLocation(files[selectedFile]);

            case 0:
                return Optional.empty();
        }
        return Optional.empty();
    }
}
