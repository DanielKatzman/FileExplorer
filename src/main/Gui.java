package main;

import java.io.File;
import java.util.Scanner;

public class Gui {
    private static final char arrowIn = '↳';
    private static final char arrowOut = '↲';
    private static EngineManager engine = new EngineManager();

    public static void loadMenu(){
        Scanner sc = new Scanner(System.in);
        boolean exit = false;
        int input = 0;

        while(!exit){
            menu();

            switch (Input.readInt()){
                case 1:
                    option1();
            }
        }
    }

    private static void menu(){
        System.out.println("-------------File Explorer-------------");
        System.out.println("1. Show all files");
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
        System.out.println("press 1 to select folder, press 2 to enter folder , press 3 to continue");
    }

    ////////////////////////////////////////options///////////////////////////////////////////////

    private static void option1(){
        System.out.println("Enter search depth");
        int depth = Input.readInt();
        engine.showAllFiles(depth);
    }

}
