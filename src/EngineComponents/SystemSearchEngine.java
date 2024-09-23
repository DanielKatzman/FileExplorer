package EngineComponents;

import exceptions.FilesIsEmpty;
import io.Gui;

import java.io.File;

public class SystemSearchEngine {
    private static int insideLoop = 0;
    private static int depth;
    private static String mainFilePath;
    private File currentFile;


    public SystemSearchEngine(int depth,String startingLocation) throws FilesIsEmpty {
        this.depth = depth;
        mainFilePath = startingLocation;
        currentFile = new File(mainFilePath);
        explorer(currentFile);
    }


    private void explorer(File currentFile) throws FilesIsEmpty {
        File[] files = currentFile.listFiles();
        if (files == null) {
            throw new FilesIsEmpty();
        }

        if (depth == insideLoop && depth != 0) { //limit the search
            Gui.printSpaces(insideLoop);
            System.out.print("Maximum depth!\n");
            return;
        }

        for (File file : files) {
            if (file.isDirectory()) {
                Gui.printDirectory(insideLoop, file);
                Gui.printArrow(insideLoop, true);
                insideLoop++;
                explorer(file); // instead of construction the whole file path and creating a new File, i can just take the file from the loop.
                insideLoop--;
                Gui.printArrow(insideLoop, false);
            } else { //no need to check for isFile() because file directory can distinguish only between directories and files.
                Gui.printFile(insideLoop, file);
            }
        }
        Gui.printNoFiles(insideLoop);

    }
}
