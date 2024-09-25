package exceptions;

import java.io.IOException;

public class FilesExceptions extends IOException {
    public FilesExceptions(String s) {
        System.out.println("Error: " + s);
    }
}
