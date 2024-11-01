package EngineComponents;

import java.io.File;
import java.util.Optional;

public abstract class Explorer {
    protected  File currentDirectory;
    protected static int insideLoop = 0;
    protected int depth;

    Explorer(int depth) {
        this.depth = depth;
    }


    protected abstract Optional<File> loop(File currentFile);
    protected abstract Optional<File> handleEmptyDirectory(File currentFile);
    protected abstract void handleDirectory(File currentFile);
    protected Optional<File> handleMaxDepth(){return Optional.empty();}
    protected void handleFile(File currentFile){}
    protected abstract Optional<File> handleReturn(File currentFile);
    public abstract Optional<File> handleNoAccess();

}
