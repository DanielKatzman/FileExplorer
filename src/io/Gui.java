package io;

import EngineComponents.EngineManager;

import java.io.File;
import java.util.Optional;
import java.util.Scanner;

public class Gui {
    private static final char arrowIn = '↳';
    private static final char arrowOut = '↲';
    private static EngineManager engine = new EngineManager();

    public static void loadMenu(){
        Scanner sc = new Scanner(System.in);

        while(true){
            menu();

            switch (Input.readInt()){
                case 1:
                    option1();
                    break;
                case 2:
                    option2();
                    break;
                case 3:
                    option3();
                    break;
                case 0:
                    option0();
                    break;
            }
        }
    }


    private static void menu(){
        System.out.println("-------------File Explorer-------------");
        System.out.println("1. Show all files");
        System.out.println("2. File Explorer");
        System.out.println("3. Write To Text");
        System.out.println("0. Exit Program");
    }

    private static void printSpaces(int insideLoop){
        System.out.print(" ".repeat(insideLoop));
    }

    public static void printArrow(int insideLoop,boolean arrowDirection){
        printSpaces(insideLoop);

        if(arrowDirection){
            System.out.print(arrowIn);
        }
        else {
            System.out.print(arrowOut);
        }
        System.out.println();
    }

    public static void printDirectory(int insideLoop, File file){
        printSpaces(insideLoop);
        System.out.printf("Directory: %s%n",file.getName());
    }

    public static void printFile(int insideLoop, File file){
        printSpaces(insideLoop);
        System.out.printf("File: %s%n",file.getName());
    }


    public static void printNoFiles(int insideLoop){
        printSpaces(insideLoop);
        System.out.println("No more files found");
    }

    public static void fileExplorerMenu(){
        System.out.println("1: select folder 2: enter folder 3: return 0: exit");
    }

    public static void printDirectoryDetails(File[] files){
        int counter = 0;
        for(File file : files){
            if(file.isDirectory()){
                System.out.printf("%-3d Directory: %-30s | Size: %-10.1f %n",counter++,file.getName(),(float)file.length());
            }
        }
    }

    public static void chooseFileFromList(){
        System.out.println("Choose a file from the list");
    }

    public static void fileLocationMessage(){
        System.out.println("-------------------file Explorer----------------------");
        System.out.println("------choose a directory to begin the search-------");
    }

    public static void searchDepthMessage(){
        System.out.println("enter search depth");
    }

    public static void selectedDirectoyMessage(File directory){
        System.out.printf("Selected: %-20s %n",directory.getName());
    }

    public static void backToTopDirectoryMessage(){
        System.out.println("This is the top Directory!");
    }

    public static void emptyDirectoryMessage(int insideLoop){
        printSpaces(insideLoop);
        System.out.print("This Directory is empty!\n");
    }

    public static void selectSystemDisk(File[] diskNames){
        int counter = 0;
        for(File file : diskNames){
            System.out.printf("Select System Disk: %n %-2d %-20s %n",counter++,file.getAbsoluteFile());
        }
    }

    public static void incorrectInputMessage(){
        System.out.println("Please enter a valid input");
    }

    ////////////////////////////////////////options///////////////////////////////////////////////

    private static void option1(){
        System.out.println("--------------Option 1 selected---------------");
        engine.showAllFiles();
    }

    private static void option2(){
        System.out.println("--------------Option 2 selected---------------");
        Optional<File> output = engine.fileExplorer();

        if(output.isPresent()){
            System.out.println(output.get().getName());
        }else System.out.println("No such file or directory");
    }

    private static void option3() {
        System.out.println("--------------Option 3 selected---------------");
        engine.writeToText();
    }

    private static void option0(){
        System.out.println("--------------goodBye!---------------");
        System.exit(0);
    }

}
