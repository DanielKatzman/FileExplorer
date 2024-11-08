package EngineComponents;

import io.Gui;
import io.Input;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Optional;

public class FileExplorer_2_0 extends Explorer{
    static int inputChoice;
    static File subDirectory;

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
        subDirectory = currentFile;

        Gui.fileExplorerMenu();

        inputChoice = Input.readInt();

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
        try {
            if(inputChoice == 3 && currentFile.listFiles().length == 0){
            }
        }catch (NullPointerException e){
            handleRoot(currentFile);
        }

        try {
            if(currentFile.exists()) {
                File[] files = currentFile.listFiles();
                File[] filteredFiles = Arrays.stream(files)
                        .filter(File::isDirectory)
                        .filter(File::canRead)
                        .toArray(File[]::new);

                if(filteredFiles.length == 0){
                    handleRoot(currentFile);
                }

                Gui.printDirectoryDetails(filteredFiles);
                Gui.chooseFileFromList();
                int inputFile = Input.correctInput(filteredFiles.length - 1);
                currentDirectory = filteredFiles[inputFile];
                Gui.selectedDirectoyMessage(currentDirectory);
            }
        }catch (NullPointerException e){
            handleNoAccess();
        }
    }

    protected Optional<File> handleRoot(File currentFile) throws IOException {
        System.out.print("cannot return current directory is the root directory!\n");
        return loop(subDirectory);
    }

}
