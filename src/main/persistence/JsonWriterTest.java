package persistence;

import model.Sudoku;
import model.SudokuNumber;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.fail;

// Based on JsonSerializationDemo shown in the Workroom example for CPSC 210

public class JsonWriterTest {
    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Sudoku sd = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderSudoku() {
        JsonReader reader = new JsonReader("./data/testSudoku.json");
        try {
            Sudoku sd = reader.read();
            for (int x = 0; x < 9; x++) {
                for (int y = 0; y < 9; y++) {
                    sd.getGrid()[x][y] = new SudokuNumber(0);
                }
            }
            assertEquals(new int[][]{{0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0}}, sd.getGrid());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}