package EngineComponents;

import exceptions.FilesExceptions;
import exceptions.FilesIsEmpty;
import exceptions.NoAccess;
import io.Gui;

import java.io.File;
import java.util.Optional;

public abstract class Explorer {
    protected static File currentFile;
    protected static int insideLoop = 0;
    protected int depth;

    Explorer(int depth) {
        this.depth = depth;
    }

    protected Optional<File> explorer(File folder){
        currentFile = folder;
        File[] files = currentFile.listFiles();

        if(files == null) handleNoAccess();

        if(files.length == 0) handleNoMoreFiles(currentFile);

        if(depth == insideLoop && depth != 0) handleMaxDepth(currentFile);

        insideLoop++;
        for (File file : files) {
            if (file.isDirectory()) {
                handleDirectory(currentFile);
                return handleReturn(currentFile);
            }else handleFile(currentFile);
        }
        insideLoop--;
        return handleNoMoreFiles(currentFile);
    }

    protected abstract Optional<File> handleNoMoreFiles(File currentFile);
    protected abstract void handleDirectory(File currentFile);
    protected void handleMaxDepth(File currentFile){}
    protected void handleFile(File currentFile){}
    protected abstract Optional<File> handleReturn(File currentFile);
    public abstract Optional<File> handleNoAccess();

}
