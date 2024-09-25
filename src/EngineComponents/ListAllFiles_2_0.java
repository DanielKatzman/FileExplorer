package EngineComponents;

import io.Gui;
import io.Input;

import java.io.File;
import java.util.Optional;

public class ListAllFiles_2_0 extends Explorer{
    public ListAllFiles_2_0(int depth,File startFile) {
        super(depth);
        currentDirectory = startFile;
    }

    public void initiate(){
        explorer(currentDirectory);
    }

    @Override
    protected Optional<File> handleNoMoreFiles(File currentFile) {
        // No more files or directories in this folder
        return Optional.empty();
    }

    @Override
    protected void handleDirectory(File currentFile) {
        // Print the directory details
        Gui.printDirectory(insideLoop, currentFile);  // Assuming Gui prints the directory name/structure
        Gui.printArrow(insideLoop,true);
    }

    @Override
    protected void handleFile(File currentFile) {
        // Print the file details
        Gui.printFile(insideLoop, currentFile);  // Assuming Gui prints the file name/structure
    }

    @Override
    protected Optional<File> handleReturn(File currentFile) {
        explorer(currentFile);
        Gui.printArrow(insideLoop,false);
        return Optional.empty();
    }

    @Override
    public Optional<File> handleNoAccess() {
        // Handle when a directory cannot be accessed
        Gui.emptyDirectoryMessage();  // Assuming Gui prints a message about access denial
        return Optional.empty();
    }
}
