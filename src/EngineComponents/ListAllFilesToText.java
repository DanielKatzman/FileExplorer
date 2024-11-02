package EngineComponents;

import io.FileWriterToText;
import java.io.File;
import java.io.IOException;
import java.util.Optional;

public class ListAllFilesToText extends Explorer{

    public ListAllFilesToText(int depth,File startFile){
        super(depth);
        currentDirectory = startFile;
    }

    public void initialise() throws IOException {
        loop(currentDirectory);
    }
    @Override
    protected Optional<File> loop(File currentFile) throws IOException {
        if(!currentFile.isDirectory()) {
            return Optional.empty();
        }

        File[] subDirectories = currentFile.listFiles();

        if(subDirectories == null) {
            handleNoAccess();
        }

        if(subDirectories.length == 0) {
            handleEmptyDirectory(currentDirectory);
        }

        if(depth == insideLoop && depth != 0){
            handleMaxDepth();
        }

        insideLoop++;
        for(File files : subDirectories) {
            if(files.isDirectory()){
                handleDirectory(files); //prints folder name
                handleReturn(files);
            }else {
                handleFile(files); //prints file name
            }
        }
        insideLoop--;
        return Optional.empty();
    }

    @Override
    protected Optional<File> handleEmptyDirectory(File currentFile) throws IOException {
        FileWriterToText.printNoFiles(insideLoop);
        return Optional.empty();
    }

    @Override
    protected void handleDirectory(File currentFile) throws IOException {
        FileWriterToText.printDirectory(insideLoop,currentFile);
        FileWriterToText.printArrow(insideLoop,true);
    }

    @Override
    protected void handleFile(File currentFile) throws IOException {
        FileWriterToText.printFile(insideLoop,currentFile);
    }

    @Override
    protected Optional<File> handleReturn(File currentFile) throws IOException {
        loop(currentFile);
        FileWriterToText.printArrow(insideLoop,false);
        return Optional.empty();
    }

    @Override
    public Optional<File> handleNoAccess() {
        return Optional.empty();
    }
}
