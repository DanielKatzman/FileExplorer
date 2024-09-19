package main;

import java.io.File;

public class SimpleFileExplorer {
    private static final char arrowIn = '\u21B3';
    private static final char arrowOut = '\u21B2';
    private static int insideLoop = 0;
    private final static String mainFilePath = "C:\\Users\\danka\\OneDrive - Afeka College Of Engineering\\Self learning";
    public static void main(String[] args) {
        File file = new File(mainFilePath);
        showAllFiles(file);
    }

    public static void showAllFiles(File filePath){
        File[] files = filePath.listFiles();
        if(files == null){
            System.out.println("No Files Found");
            return;
        }
        for(File file : files){
            if(file.isDirectory()){
                spaceMaker();
                System.out.println("Directory: " + file.getAbsolutePath());
                spaceMaker();
                System.out.print(arrowIn);
                System.out.println();
                insideLoop++;
                showAllFiles(new File(filePath.getAbsolutePath() + File.separator + file.getName()));
                insideLoop--;
                spaceMaker();
                System.out.print(arrowOut);
                System.out.println();
            }else if(file.isFile()){
                for (int i = 0; i < insideLoop + 1;i++) {
                    System.out.print(" ");
                }
                System.out.println("File: " + file.getName());
            }
        }
        spaceMaker();
        System.out.print("No more files found in directory: " + filePath.getName());
        System.out.println();
    }

    private static void spaceMaker(){
        for(int i = 0; i < insideLoop; i++){
            System.out.print(" ");
        }
    }
}
