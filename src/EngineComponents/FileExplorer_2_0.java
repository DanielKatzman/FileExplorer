package EngineComponents;

import io.Gui;
import io.Input;
import java.io.File;
import java.util.Optional;

public class FileExplorer_2_0 extends Explorer{

    public FileExplorer_2_0(){
        super(0); //no limit on depth!
    }



    public Optional<File> initiate(){
        File[] files = File.listRoots();
        Gui.selectSystemDisk(files);
        int input = Input.correctInput(files.length - 1);
        currentDirectory = files[input];
        return loop(currentDirectory);
    }

    @Override
    protected Optional<File> loop(File currentFile) {
        handleDirectory(currentFile);
        handleReturn(currentFile);
        return Optional.of(currentDirectory);
    }

    @Override
    protected Optional<File> handleEmptyDirectory(File currentFile){
        System.out.printf("No more files in Directory! returning to Directory: %s %n", currentDirectory.getParentFile().getName());
        return loop(currentFile.getParentFile());
    }

    @Override
    protected Optional<File> handleReturn(File currentFile){

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

    public Optional<File> handleNoAccess(){
        System.out.printf("No access to current directory! returning to Directory: %s %n", currentDirectory.getParentFile().getName());
        return loop(currentDirectory.getParentFile());
    }

    protected void handleDirectory(File currentFile){
        File[] files = currentFile.listFiles(File::isDirectory);
        Gui.printDirectoryDetails(files);
        Gui.chooseFileFromList();
        int inputFile = Input.correctInput(files.length - 1);
        currentDirectory = files[inputFile];
        Gui.selectedDirectoyMessage(currentDirectory);
    }
}
