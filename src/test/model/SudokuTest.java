package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SudokuTest {
    private Sudoku testSudoku;

    @BeforeEach
    void runBefore() {
        testSudoku = new Sudoku("test sudoku");
    }

    @Test
    void testConstructor() {
        assertEquals(testSudoku.getGrid(),new SudokuNumber[9][9]);
    }

    @Test
    void testMeetsCriteria() {
        assertTrue(testSudoku.meetsCriteria(1, 1, 2));
        assertFalse(testSudoku.meetsCriteria(1, 1, 1));
    }

    @Test
    void testNotInSquare() {
        assertTrue(testSudoku.notInSquare(1, 2, 1));
        assertFalse(testSudoku.notInSquare(1, 4, 2));
    }

    @Test
    void testNotInRow() {
        assertTrue(testSudoku.notInRow(1, 2));
        assertFalse(testSudoku.notInRow(1, 6));
    }

    @Test
    void testNotInColumn() {
        assertTrue(testSudoku.notInColumn(1, 2));
        assertFalse(testSudoku.notInColumn(1, 1));
    }

    @Test
    void testGetGrid() {
        assertEquals(new int[][]{{0, 0, 6, 0, 4, 0, 0, 9, 7}, {0, 4, 0, 7, 3, 0, 0, 1, 0},
                {0, 1, 7, 0, 9, 2, 9, 3, 0}, {6, 0, 0, 0, 7, 0, 0, 8, 0},
                {1, 0, 5, 0, 6, 0, 9, 0, 3}, {0, 2, 0, 0, 1, 0, 0, 0, 6}, {0, 5, 0, 9, 8, 0, 1, 6, 0},
                {0, 9, 0, 0, 5, 6, 0, 7, 0}, {8, 6, 0, 0, 2, 0, 3, 0, 0}}, testSudoku.getGrid());
    }

    @Test
    void testGetSidelength() {
        assertEquals(9, testSudoku.getSideLength());
    }
}



        /*assertTrue(testSudoku.notInRow(new int[][]{{0, 0, 6, 0, 4, 0, 0, 9, 7}, {0, 4, 0, 7, 3, 0, 0, 1, 0},
                {0, 1, 7, 0, 9, 2, 9, 3, 0}, {6, 0, 0, 0, 7, 0, 0, 8, 0},
                {1, 0, 5, 0, 6, 0, 9, 0, 3}, {0, 2, 0, 0, 1, 0, 0, 0, 6}, {0, 5, 0, 9, 8, 0, 1, 6, 0},
                {0, 9, 0, 0, 5, 6, 0, 7, 0}, {8, 6, 0, 0, 2, 0, 3, 0, 0}}, 1, 2));
        assertFalse(testSudoku.notInRow(/*new int[][]{{0, 0, 6, 0, 4, 0, 0, 9, 7}, {0, 4, 0, 7, 3, 0, 0, 1, 0},
                {0, 1, 7, 0, 9, 2, 9, 3, 0}, {6, 0, 0, 0, 7, 0, 0, 8, 0},
                {1, 0, 5, 0, 6, 0, 9, 0, 3}, {0, 2, 0, 0, 1, 0, 0, 0, 6}, {0, 5, 0, 9, 8, 0, 1, 6, 0},
                {0, 9, 0, 0, 5, 6, 0, 7, 0}, {8, 6, 0, 0, 2, 0, 3, 0, 0}}, 1, 6)); */