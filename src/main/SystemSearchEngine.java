package main;

import exceptions.FilesIsEmpty;

import java.io.File;

public class SystemSearchEngine {
    private static final char arrowIn = '↳';
    private static final char arrowOut = '↲';
    private static int insideLoop = 0;
    private static int depth;
    private static String mainFilePath;
    private static boolean exit = false;
    private File currentFile;
    private static String currentFilePath; //todo fix so i wouldn't need a static String!!!

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

    public static String fileLocationToString(File file) throws FilesIsEmpty {
        File[] files = file.listFiles();

        if (files == null) {
            throw new FilesIsEmpty();
        }

        for (File currentFile : files) {
            if(exit){
                break;
            }
            if (currentFile.isDirectory()) {
                Gui.printDirectory(insideLoop, currentFile);
                Gui.fileLocationQuestion();
                switch (Input.readInt()){
                    case 1:
                        exit = true;
                         currentFilePath = currentFile.getAbsolutePath();
                         break;
                    case 2:
                        fileLocationToString(currentFile);
                    default:
                        continue;
                }
            }
        }
        return currentFilePath;
    }
}
