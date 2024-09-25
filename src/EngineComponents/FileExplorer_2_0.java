package EngineComponents;

import exceptions.FilesExceptions;
import exceptions.FilesIsEmpty;
import io.Gui;
import io.Input;
import java.io.File;
import java.util.Optional;

public class FileExplorer_2_0 extends Explorer{
    private File chosenFile;

    public FileExplorer_2_0(){
        super(0); //no limit on depth!
    }

    public Optional<File> initiate(){
        File[] files = File.listRoots();
        Gui.selectSystemDisk(files);
        int input = Input.correctInput(files.length - 1);
        File currentFile = files[input];
        return explorer(currentFile);
    }

    @Override
    protected Optional<File> handleNoMoreFiles(File currentFile){
        System.out.printf("No more files in Directory! returning to Directory: %s %n", chosenFile.getParentFile().getName());
        return explorer(currentFile.getParentFile());
    }

    @Override
    protected Optional<File> handleReturn(File currentFile){

        Gui.fileExplorerMenu();

        int inputChoice = Input.readInt();

        return switch (inputChoice) {
            case 0 -> Optional.empty(); //Exit statement
            case 1 -> Optional.of(chosenFile); //choose the file
            case 2 -> explorer(chosenFile); //Enter folder
            case 3 -> explorer(currentFile.getParentFile()); //return to previous folder
            default -> {
                System.out.println("Invalid input");
                yield explorer(currentFile);
            }
        };
    }

    public Optional<File> handleNoAccess(){
        System.out.printf("No access to current directory! returning to Directory: %s %n", chosenFile.getParentFile().getName());
        return explorer(chosenFile.getParentFile());
    }

    protected void handleDirectory(File currentFile){
        File[] files = currentFile.listFiles(File::isDirectory);
        Gui.printDirectoryDetails(files);
        Gui.chooseFileFromList();
        int inputFile = Input.correctInput(files.length - 1);
        chosenFile = files[inputFile];
        Gui.selectedDirectoyMessage(chosenFile);
    }
}
