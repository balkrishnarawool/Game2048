package com.balkrishnarawool.android.game2048.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by balkrishna on 01/05/2017.
 */

public class EngineTest {

    private Engine engine;

    @Before
    public void begin() {
        engine = new Engine();
    }

    @Test
    public void testPutNumberRandomly() throws Exception {
        int[][] cells = engine.getCells();

        // Assert that all cells are empty.
        for (int i=0; i<cells.length; i++) {
            for (int j=0; j<cells[i].length; j++) {
                assertEquals(cells[i][j], 0);
            }
        }

        assertEquals(countEmptyCells(cells), 16);

        // Put a random 2 or 4.
        for (int i=0; i<16; i++) {
            engine.putNumberRandomly();
            int count = countEmptyCells(cells);
            assertEquals(count, 15-i);
        }

        assertEquals(countEmptyCells(cells), 0);

    }

    @Test
    public void testMoveCellsRight() throws Exception {
        int[][] cells = engine.getCells();

        cells[0][0] = 0;
        cells[0][1] = 0;
        cells[0][2] = 2;
        cells[0][3] = 0;

        cells[1][0] = 0;
        cells[1][1] = 4;
        cells[1][2] = 0;
        cells[1][3] = 4;

        cells[2][0] = 0;
        cells[2][1] = 8;
        cells[2][2] = 0;
        cells[2][3] = 16;

        cells[3][0] = 32;
        cells[3][1] = 32;
        cells[3][2] = 32;
        cells[3][3] = 32;

        int count = engine.moveCellsRight();

        assertEquals(count, 1+2+1+4);

        assertEquals(cells[0][0], 0);
        assertEquals(cells[0][1], 0);
        assertEquals(cells[0][2], 0);
        assertEquals(cells[0][3], 2);

        assertEquals(cells[1][0], 0);
        assertEquals(cells[1][1], 0);
        assertEquals(cells[1][2], 0);
        assertEquals(cells[1][3], 8);

        assertEquals(cells[2][0], 0);
        assertEquals(cells[2][1], 0);
        assertEquals(cells[2][2], 8);
        assertEquals(cells[2][3], 16);

        assertEquals(cells[3][0], 0);
        assertEquals(cells[3][1], 0);
        assertEquals(cells[3][2], 64);
        assertEquals(cells[3][3], 64);

    }

    private int countEmptyCells(int[][] cells) {
        int count = 0;

        for (int i=0; i<cells.length; i++) {
            for (int j=0; j<cells[i].length; j++) {
                if(cells[i][j] == 0) count++;
            }
        }

        return count;
    }


}

