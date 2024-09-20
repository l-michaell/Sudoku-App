package persistence;

import model.Sudoku;
import model.SudokuNumber;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

// Based on JsonSerializationDemo shown in the Workroom example for CPSC 210

class JsonReaderTest {

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
        SudokuNumber n = new SudokuNumber(0);
        try {
            Sudoku sd = reader.read();
            for (int x = 0; x < 9; x++) {
                for (int y = 0; y < 9; y++) {
                    sd.getGrid()[x][y] = new SudokuNumber(0);
                }
            }
            assertEquals(new SudokuNumber[][]{{n, n, n, n, n, n, n, n, n}, {n, n, n, n, n, n, n, n, n},
                    {n, n, n, n, n, n, n, n, n}, {n, n, n, n, n, n, n, n, n}, {n, n, n, n, n, n, n, n, n},
                    {n, n, n, n, n, n, n, n, n}, {n, n, n, n, n, n, n, n, n}, {n, n, n, n, n, n, n, n, n},
                    {n, n, n, n, n, n, n, n, n}}, sd.getGrid());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
