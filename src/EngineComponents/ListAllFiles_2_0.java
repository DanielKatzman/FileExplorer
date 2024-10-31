package EngineComponents;

import io.Gui;

import java.io.File;
import java.util.Optional;

public class ListAllFiles_2_0 extends Explorer{
    public ListAllFiles_2_0(int depth,File startFile) {
        super(depth);
        currentDirectory = startFile;
    }

    public void initiate(){
        loop(currentDirectory);
    }
    @Override
    protected Optional<File> loop(File currentFile) {

        if(currentDirectory == null) {
            handleNoAccess();
        }
        if(currentDirectory.listFiles().length == 0) { //todo check if the length is the real length of the inside folders!!
            handleEmptyDirectory(currentDirectory);
        }

        if(depth == insideLoop && depth != 0){
            handleMaxDepth();
        }
        insideLoop++;
        for(File files : currentDirectory.listFiles()){
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

    //    public void initiate(){
//        explorer(currentDirectory);
//    }

    @Override
    protected Optional<File> handleEmptyDirectory(File currentFile) {
        // No more files or directories in this folder
        Gui.emptyDirectoryMessage(insideLoop);
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
        currentDirectory = currentFile;
        loop(currentDirectory);
        Gui.printArrow(insideLoop,false);
        return Optional.empty();
    }

    @Override
    public Optional<File> handleNoAccess() {
        // Handle when a directory cannot be accessed
        Gui.emptyDirectoryMessage(insideLoop);  // Assuming Gui prints a message about access denial
        return Optional.empty();
    }
}
