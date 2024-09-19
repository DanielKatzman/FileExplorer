# Simple Recursive File Explorer

This is a simple recursive file explorer built in Java. The program recursively explores directories starting from a specified root folder and lists all the files and directories found within it. The output includes clear visual indicators to represent when the program is entering and exiting directories using custom arrow symbols (`↳` for entering and `↲` for exiting).

## Features - Recursively explores directories and subdirectories. - Lists all files and directories, differentiating between them. - Visual indication of entering and exiting directories using arrows (`↳` and `↲`). - Handles empty directories gracefully. - Fully customizable starting directory.

## Requirements - Java 8 or later

## Usage

### Running the Program 1. Clone or download the source code. 2. Update the starting directory in the `mainFilePath` variable if necessary: ```java private final static String mainFilePath = "C:\\path\\to\\your\\directory"; ``` 3. Compile the program: ```bash javac SimpleFileExplorer.java ``` 4. Run the program: ```bash java SimpleFileExplorer ```

### Example Output

When exploring the directory structure, the program produces output like this:

