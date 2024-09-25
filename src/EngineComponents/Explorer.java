package EngineComponents;

import exceptions.FilesExceptions;
import exceptions.FilesIsEmpty;
import exceptions.NoAccess;
import io.Gui;

import java.io.File;
import java.util.Optional;

public abstract class Explorer {
    protected static File currentDirectory;
    protected static int insideLoop = 0;
    protected int depth;

    Explorer(int depth) {
        this.depth = depth;
    }

    protected Optional<File> explorer(File folder){
        currentDirectory = folder;
        File[] files = folder.listFiles();

        if(files == null) handleNoAccess();

        if(files.length == 0) handleNoMoreFiles(currentDirectory);

        if(depth == insideLoop && depth != 0) handleMaxDepth();

        insideLoop++;

        for (File file : files) {
            if (file.isDirectory()) {
               handleDirectory(currentDirectory);

                Optional<File> result = handleReturn(file);
                if (result.isPresent()) {
                    insideLoop--;
                    return result;
                }
            }else handleFile(file);
        }
        insideLoop--;
        return handleNoMoreFiles(currentDirectory);
    }

    protected abstract Optional<File> handleNoMoreFiles(File currentFile);
    protected abstract void handleDirectory(File currentFile);
    protected Optional<File> handleMaxDepth(){return Optional.empty();}
    protected void handleFile(File currentFile){}
    protected abstract Optional<File> handleReturn(File currentFile);
    public abstract Optional<File> handleNoAccess();

}
