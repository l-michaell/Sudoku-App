package ui;

import model.Sudoku;
import model.SudokuNumber;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;


// Sudoku solver application
public class SudokuApp {
    private static final String JSON_STORE = "./data/sudoku.java";
    private Scanner input;
    private Sudoku sudoku;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: runs the sudoku application
    public SudokuApp() {
        input = new Scanner(System.in);
        sudoku = new Sudoku("Your sudoku");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runSudoku();
    }


    private void runSudoku() {
        boolean keepGoing = true;
        String command = null;

        init();

        while (keepGoing) {
            showMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("f")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }
        System.out.println("\n G'day");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("a")) {
            System.out.println("Enter your sudoku from left to right, top to bottom: ");
            inputSudoku();
        } else if (command.equals("b")) {
            if (checkCompletable()) {
                printSudoku();
                System.out.print("Sudoku has a solution!");
            } else {
                System.out.print("Sudoku does not have a solution");
            }
            System.out.println();
        } else if (command.equals("c")) {
            saveSudoku();
        } else if (command.equals("d")) {
            loadSudoku();
        } else if (command.equals("e")) {
            printSudoku();
        } else {
            System.out.println("Please enter a valid command");
        }
    }

    // EFFECTS: shows menu fo selections for user
    private void showMenu() {
        System.out.println("Type a to add numbers to your sudoku");
        System.out.println("Type b to check if your sudoku has a solution");
        System.out.println("Type c to save your what you entered");
        System.out.println("Type d to load what you saved");
        System.out.println("Type e to print your sudoku");
        System.out.println("Type f to end the program");
    }

    // Using https://stackoverflow.com/questions/29546564/convert-string-into-a-two-dimensional-array
    // as basis for converting input into sudoku
    // MODIFIES: this
    // EFFECTS: checks if sudoku can be completed
    private boolean checkCompletable() {
        if (sudoku.hasSolution(sudoku.getGrid())) {
            return true;
        } else {
            return false;
        }
    }

    // EFFECTS: prints sudoku
    private void printSudoku() {
        for (int row = 0; row < sudoku.getSideLength(); row++) {
            System.out.println();
            for (int col = 0; col < sudoku.getSideLength(); col++) {
                System.out.print(sudoku.getGrid()[row][col].getNumber());
            }
        }
        System.out.println();
    }

    // MODIFIES: this
    // EFFECTS: adds user input into current sudoku
    private void inputSudoku() {
        sudoku = new Sudoku("Your sudoku");
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                sudoku.getGrid()[x][y] = new SudokuNumber(input.nextInt());
            }
        }
    }

    // EFFECTS: sudoku saves to file
    private void saveSudoku() {
        try {
            jsonWriter.open();
            jsonWriter.write(sudoku);
            jsonWriter.close();
            System.out.println("Saved " + sudoku.getName() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads the sudoku from file
    private void loadSudoku() {
        try {
            sudoku = jsonReader.read();
            System.out.println("Loaded " + sudoku.getName() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: initializes program
    private void init() {
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }
}
