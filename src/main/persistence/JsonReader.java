package persistence;

import model.Sudoku;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import model.SudokuNumber;
import org.json.*;

// Represents a reader that reads a sudoku from JSON data stored in file,
// Based on JsonSerializationDemo shown in the Workroom example for CPSC 210
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads Sudoku from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Sudoku read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseSudoku(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses sudoku from JSON object and returns it
    private Sudoku parseSudoku(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        Sudoku sd = new Sudoku(name);
        addNumbers(sd, jsonObject);
        return sd;
    }

    // MODIFIES: sd
    // EFFECTS: parses grid from JSON object and adds them sudoku
    private void addNumbers(Sudoku sd, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("grid");
        int row = 0;
        int col = 0;

        for (Object json : jsonArray) {
            JSONObject nextSudokuNumber = (JSONObject) json;
            addNumber(sd, nextSudokuNumber, row, col);
            col++;
            if (col >= 9) {
                col = 0;
                row++;
            }
        }
    }

    // MODIFIES: sd
    // EFFECTS: parses sudoku numbers from JSON Object and adds it to sudoku
    private void addNumber(Sudoku sd, JSONObject jsonObject, int row, int column) {
        int number = jsonObject.getInt("number");
        sd.getGrid()[row][column] = new SudokuNumber(number);
    }
}
