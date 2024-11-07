package EngineComponents;

import io.Gui;
import io.Input;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Optional;

public class FileExplorer_2_0 extends Explorer{

    public FileExplorer_2_0(){
        super(0); //no limit on depth!
    }



    public Optional<File> initiate() throws IOException {
        File[] files = File.listRoots();
        Gui.selectSystemDisk(files);
        int input = Input.correctInput(files.length - 1);
        currentDirectory = files[input];
        return loop(currentDirectory);
    }

    @Override
    protected Optional<File> loop(File currentFile) throws IOException {
        handleDirectory(currentFile);
        handleReturn(currentFile);
        return Optional.of(currentDirectory);
    }

    @Override
    protected Optional<File> handleEmptyDirectory(File currentFile) throws IOException {
        System.out.printf("No more Directories! returning to Directory: %s %n", currentDirectory.getParentFile().getName());
        return loop(currentFile.getParentFile());
    }

    @Override
    protected Optional<File> handleReturn(File currentFile) throws IOException {

        Gui.fileExplorerMenu();

        int inputChoice = Input.readInt();

        return switch (inputChoice) {
            case 0 -> Optional.empty(); //Exit statement
            case 1 -> Optional.of(currentDirectory); //choose the file
            case 2 -> loop(currentDirectory); //Enter folder
            case 3 -> loop(currentFile.getParentFile()); //return to previous folder
            default -> {
                System.out.println("Invalid input");
                yield loop(currentFile);
            }
        };
    }

    public Optional<File> handleNoAccess() throws IOException {
        System.out.printf("No access to current directory! returning to Directory: %s %n", currentDirectory.getParentFile().getName());
        return loop(currentDirectory.getParentFile());
    }

    protected void handleDirectory(File currentFile) throws IOException {
        if(currentFile.exists() && currentFile.isDirectory()) {
            File[] files = currentFile.listFiles();
            File[] filteredFiles = Arrays.stream(files)
                    .filter(File::isDirectory)
                    .filter(File::canRead)
                    .filter(file -> !file.isHidden())
                    .filter(File::isAbsolute)
                    .toArray(File[]::new);

            if(filteredFiles.length == 0){
                handleEmptyDirectory(currentFile);
            }

            Gui.printDirectoryDetails(filteredFiles);
            Gui.chooseFileFromList();
            int inputFile = Input.correctInput(filteredFiles.length - 1);
            currentDirectory = filteredFiles[inputFile];
            Gui.selectedDirectoyMessage(currentDirectory);
        }
        else {
            handleNoAccess();
        }

    }
}
