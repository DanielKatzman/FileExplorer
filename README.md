
# Simple File Explorer

A simple file explorer application written in Java that allows users to navigate through the file system, view directory contents, and perform various file operations. This project provides a user-friendly interface for exploring files and directories on your computer.

## Table of Contents
- [Overview](#overview)
- [Features](#features)
- [Classes](#classes)
- [Setup](#setup)
- [Usage, Contributing, and License](#usage-contributing-and-license)

## Overview
The Simple File Explorer is designed to help users efficiently browse and manage files on their system. It includes:
- An abstract class `Explorer` that defines the core functionality for directory navigation.
- Concrete implementations for displaying directory contents and writing file paths to a text file.

## Features
- Navigate through directories and view files.
- Handle empty directories gracefully.
- Return to the previous directory or exit the explorer.
- Custom GUI for user interaction.
- Implementation for listing all files starting from a selected folder.
- Functionality to write all file paths to a text file.

## Classes

### 1. `Explorer` (Abstract Class)
Defines the essential methods and attributes for file exploration. This class serves as the base for specific explorer implementations.

- **Attributes**:
    - `File currentDirectory`: The currently selected directory.
    - `int depth`: The depth of exploration, indicating how deep the explorer can go in the directory tree.

- **Methods**:
    - `loop(File currentFile)`: Abstract method for looping through files and directories.
    - `handleEmptyDirectory(File currentFile)`: Handles scenarios when the directory is empty.
    - `handleDirectory(File currentFile)`: Abstract method for processing a directory.
    - `handleReturn(File currentFile)`: Handles user navigation choices.

### 2. `FileExplorer_2_0`
Implements the functionality to explore directories through a graphical interface.

- **Methods**:
    - `initiate()`: Starts the exploration from system root directories.
    - `loop(File currentFile)`: Manages directory exploration.
    - `handleDirectory(File currentFile)`: Displays the contents of accessible subdirectories.

### 3. Additional Implementations
Additional classes extend the `Explorer` class to provide functionality for:
- Displaying all files within a selected directory.
- Writing file paths to a text file.

## Setup

To run this project, ensure you have:
1. **Java JDK 17 or higher** installed.
2. **Clone the repository**:
   ```bash
   git clone https://github.com/DanielKatzman/FileExplorer
   cd SimpleFileExplorer
   ```
3. **Build the project** using your preferred IDE or by compiling directly in the terminal.

## Usage, Contributing, and License

1. **Run the Main Program**: Start the application to launch the file explorer GUI.
2. **Navigate Directories**: Follow the prompts in the GUI to select directories and view their contents.
3. **Choose Options**: Use the options presented in the GUI to navigate back, select files, or exit the application.

Contributions are welcome! If you have suggestions or improvements, please fork the repository, make changes, and submit a pull request.

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.
