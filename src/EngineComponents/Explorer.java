package EngineComponents;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

public abstract class Explorer {
    protected  File currentDirectory;
    protected static int insideLoop = 0;
    protected int depth;

    Explorer(int depth) {
        this.depth = depth;
    }


    protected abstract Optional<File> loop(File currentFile) throws IOException;
    protected abstract Optional<File> handleEmptyDirectory(File currentFile) throws IOException;
    protected abstract void handleDirectory(File currentFile) throws IOException;
    protected Optional<File> handleMaxDepth(){return Optional.empty();}
    protected void handleFile(File currentFile) throws IOException {}
    protected abstract Optional<File> handleReturn(File currentFile) throws IOException;
    public abstract Optional<File> handleNoAccess();

}
