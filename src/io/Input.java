package io;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Input {
    private static final Scanner scanner = new Scanner(System.in);
    private static boolean exit = false;

    public static int readInt() {
        int integer = 0;
        exit = false;
        while (!exit) {
            try {
                integer = scanner.nextInt();
                exit = true;
            } catch (InputMismatchException e) {
                System.out.println("Please enter an integer");
                scanner.next();
            }
        }
        return integer;
    }

    public static String readString() {
        String string = "";
        exit = false;
        while (!exit) {
            try {
                string = scanner.nextLine();
                exit = true;
            } catch (InputMismatchException e) {
                System.out.println("Please enter an integer");
            }
        }
        return string;
    }
}
