package io;

import EngineComponents.EngineManager;

import java.io.File;
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
                case 0:
                    option0();
            }
        }
    }

    private static void menu(){
        System.out.println("-------------File Explorer-------------");
        System.out.println("1. Show all files");
        System.out.println("0. Exit Program");
    }

    public static void printSpaces(int insideLoop){
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

    public static void fileLocationQuestion(){
        System.out.println("press 1 to select folder, press 2 to enter folder , press 0 to exit");
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
        System.out.println("-------------------file guide----------------------");
        System.out.println("------choose a directory to begin the search-------");
    }

    public static void searchDepthMessage(){
        System.out.println("enter search depth");
    }

    public static void selectedDirectoyMessage(File directory){
        System.out.printf("Selected: %-20s %n",directory.getName());
    }

    ////////////////////////////////////////options///////////////////////////////////////////////

    private static void option1(){
        System.out.println("--------------Option 1 selected---------------");
        engine.showAllFiles();
    }

    private static void option0(){
        System.out.println("--------------goodBye!---------------");
        System.exit(0);
    }

}
