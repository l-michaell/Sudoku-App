package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

public class Sudoku implements Writable {
    private String name;
    private SudokuNumber[][] grid;
    private static int sideLength = 9;
    public int Testing = 0;


    // REQUIRES: grid is 9 by 9 array
    // EFFECTS: sudoku is created with being represented by grid size of sideLength * sideLength
    public Sudoku(String name) {
        this.name = name;
        grid = new SudokuNumber[sideLength][sideLength];
    }

    //EFFECTS: produces true if currentNum can be put into [currentRow][currentCol] without breaking sudoku rules
    public Boolean meetsCriteria(int currentCol, int currentRow, int currentNum) {
        return notInColumn(currentCol, currentNum) && notInRow(currentRow, currentNum)
                && notInSquare(currentCol, currentRow, currentNum);
    }

    //
    //EFFECTS: produces true if currentNum is not in square
    public boolean notInSquare(int currentCol, int currentRow, int currentNum) {
        int squareLength = 3;
        int subgridRow = currentRow - currentRow % 3;
        int subgridCol = currentCol - currentCol % 3;

        for (int x = subgridRow; x < subgridRow + squareLength; x++) {
            for (int y = subgridCol; y < subgridCol + squareLength; y++) {
                if (grid[x][y].getNumber() == currentNum) {
                    return false;
                }
            }
        }
        return true;
    }


    // EFFECTS: produces true if currentNum is not in row
    public boolean notInRow(int currentRow, int currentNum) {
        for (int x = 0; x < sideLength; x++) {
            if (grid[currentRow][x].getNumber() == currentNum) {
                return false;
            }
        }
        return true;
    }

    // EFFECTS: produces true if currentNum is not in column
    public Boolean notInColumn(int currentCol, int currentNum) {
        for (int x = 0; x < sideLength; x++) {
            if (grid[x][currentCol].getNumber() == currentNum) {
                return false;
            }
        }
        return true;
    }

    public static int getSideLength() {
        return sideLength;
    }

    public SudokuNumber[][] getGrid() {
        return grid;
    }

    // EFFECTS: produce true and fills in grid if grid has solution
    // MODIFIES: this
    public boolean hasSolution(SudokuNumber[][] grid) {
        for (int currentCol = 0; currentCol < sideLength; currentCol++) {
            for (int currentRow = 0; currentRow < sideLength; currentRow++) {
                if (grid[currentRow][currentCol].getNumber() == 0) {
                    for (int testNum = 1; testNum <= sideLength; testNum++) {
                        Testing += 1;
                        System.out.println(Testing);
                        if (meetsCriteria(currentCol, currentRow, testNum)) {
                            grid[currentRow][currentCol] = new SudokuNumber(testNum);

                            if (hasSolution(grid)) {
                                return true;
                            } else {
                                grid[currentRow][currentCol] = new SudokuNumber(0);
                            }
                        }
                    }
                    // EventLog.getInstance().logEvent(new Event("Checked a non-solvable sudoku"));
                    return false;
                }
            }
        }
        EventLog.getInstance().logEvent(new Event("Produced the solution to a sudoku"));
        return true;

    }

    public boolean hasStartingSolution(SudokuNumber[][] grid) {
        for (int currentCol = 0; currentCol < sideLength; currentCol++) {
            for (int currentRow = 0; currentRow < sideLength; currentRow++) {
                int tempNum = grid[currentRow][currentCol].getNumber();
                if (tempNum != 0) {
                    grid[currentRow][currentCol] = new SudokuNumber(0);
                    if (!meetsCriteria(currentCol, currentRow, tempNum)) {
                        EventLog.getInstance().logEvent(new Event("Given a non-solvable sudoku"));
                        grid[currentRow][currentCol] = new SudokuNumber(tempNum);
                        return false;
                    }
                    grid[currentRow][currentCol] = new SudokuNumber(tempNum);
                }
            }
        }
        return true;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("grid", gridToJson());
        return json;
    }

    // EFFECTS: returns sudokuNumbers in this grid as a json object
    private JSONArray gridToJson() {
        JSONArray jsonArray = new JSONArray();

        for (SudokuNumber[] numList : grid) {
            for (SudokuNumber s : numList) {
                jsonArray.put(s.toJson());
            }

        }
        return jsonArray;
    }

    public String getName() {
        return name;
    }
}
