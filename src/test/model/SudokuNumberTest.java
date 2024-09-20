package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SudokuNumberTest {
    private SudokuNumber sudokuNumber;

    @Test
    void testConstructor() {
        sudokuNumber = new SudokuNumber(6);
        assertEquals(6, sudokuNumber.getNumber());
    }
}
