package EngineComponents;

import io.Gui;
import io.Input;

import java.io.File;
import java.util.Optional;

public class ListAllFiles_2_0 extends Explorer{
    private File currentDirectory;
    public ListAllFiles_2_0(int depth,File startFile) {
        super(depth);
        currentDirectory = startFile;
    }

    public void initiate(){
        explorer(currentDirectory);
    }

    @Override
    protected Optional<File> handleNoMoreFiles(File currentFile) {
        Gui.printNoFiles(insideLoop);
        Gui.printArrow(insideLoop,false);
        return Optional.empty();
    }

    @Override
    protected void handleDirectory(File currentFile) {
        Gui.printArrow(insideLoop,true);
        Gui.printDirectory(insideLoop,currentFile);
    }

    @Override
    protected Optional<File> handleReturn(File currentFile) {
        // Get all directories in the current folder
        File[] directories = currentFile.listFiles(File::isDirectory);

        if (directories == null || directories.length == 0) {
            return Optional.empty();  // No subdirectories or access issue
        }

        // Recursively explore each subdirectory
        for (File directory : directories) {
            explorer(directory);  // Explore each directory recursively
        }

        // Once all directories have been explored, return empty
        return Optional.empty();
    }

    @Override
    public Optional<File> handleNoAccess() {
        Gui.printNoFiles(insideLoop);
        return Optional.empty();
    }

    @Override
    protected void handleFile(File currentFile) {
        Gui.printFile(insideLoop,currentFile);
    }
}
