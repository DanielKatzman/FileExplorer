package EngineComponents;

import exceptions.FilesIsEmpty;
import io.Gui;
import io.Input;

import java.io.File;
import java.util.Optional;

public class FileExplorer {

    public Optional<File> initiate() throws FilesIsEmpty {
        File[] files = File.listRoots();
        Gui.selectSystemDisk(files);
        int input = Input.correctInput(files.length - 1);
        return fileLocation(files[input]);
    }


    private Optional<File> fileLocation(File currentDirectory)throws FilesIsEmpty {
        File parentDir = currentDirectory.getParentFile();
        File[] files = currentDirectory.listFiles(File::isDirectory);
        if (files == null) {
            throw new FilesIsEmpty();
        }
        if(files.length == 0) {
            Gui.emptyDirectoryMessage();
            return fileLocation(parentDir);
        }

        Gui.printDirectoryDetails(files);
        Gui.chooseFileFromList();
        int selectedFile = Input.readInt();
        Gui.selectedDirectoyMessage(files[selectedFile]);

        Gui.fileExplorerMenu();
        int fileOptions = Input.readInt();

        switch (fileOptions) {
            case 1:
                return Optional.of(files[selectedFile]);

            case 2:
                return fileLocation(files[selectedFile]);

            case 3:
                if(parentDir != null) {
                    return fileLocation(parentDir);
                }else {
                    Gui.backToTopDirectoryMessage();
                    return fileLocation(currentDirectory);
                }

            case 0:
                return Optional.empty();

            default:
                System.out.println("Invalid option");
                return fileLocation(currentDirectory);
        }
    }
}
