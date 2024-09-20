package model;

import org.json.JSONObject;

public class SudokuNumber {
    private int number;

    // Represents a sudoku number having a value of number
    public SudokuNumber(int number) {
        this.number = makeInRange(number);
    }

    private int makeInRange(int number) {
        if (number >= 1 && number <= 9) {
            return number;
        }
        return 0;
    }

    public int getNumber() {
        return number;
    }

    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("number", number);
        return json;
    }
}
