package com.balkrishnarawool.android.game2048.model;

import java.util.ArrayList;
import java.util.List;

// Engine class for 2048 game
public class Engine {

    // cells represent the grid of cells in the game.
    int[][] cells = new int[4][4];
    // score
    int score = 0;

    // This method put a 2 or 4 at a random empty cell.
    public void putNumberRandomly() {

        // List empty cells.
        List<Coords> emptyCells = new ArrayList<>();
        for (int i=0; i<cells.length; i++) {
            for (int j=0; j<cells[i].length; j++) {
                if(cells[i][j] == 0) emptyCells.add(new Coords(i,j));
            }
        }

        if(emptyCells.size() > 0) {

            // Select one randomly.
            int random = (int) (Math.random() * emptyCells.size());
            Coords randomCell = emptyCells.get(random);

            // Select what to put: 2 or 4
            int value = (int) (Math.random() * 2) == 0 ? 2 : 4;

            // Put 'value' at 'randomCell'.
            cells[randomCell.row][randomCell.column] = value;

        }

    }

    public int[][] getCells() {
        return cells;
    }
    public int getScore() {
        return score;
    }

    // Move cells right. Returns number of cells moved.
    public int moveCellsRight() {
        // count: count of number of cells moved.
        int count = 0;
        // index: a placeholder for remembering cell.
        int index;
        // indexFound: indicates if 'index' is already found. Initialized to false.
        boolean indexFound = false;

        // For every row (from top to bottom),
        for (int i=0; i<cells.length; i++) {

            indexFound = false;
            // It is initialized to one cell right off the rightmost cell.
            index = 4;

            outer: while (index > 0) {

                if (!indexFound) {
                    // Set index to one cell left off index.
                    index = index - 1;
                    indexFound = true;

                    // Start from index cell, go left. Move first non-empty cell to index place.
                    int j = index;
                    for (; j >= 0; j--) {
                        if (cells[i][j] != 0) {
                            cells[i][index] = cells[i][j];
                            if(j != index) cells[i][j] = 0;
                            count += (index - j);
                            break;
                        }
                    }
                    if(j == -1) break outer;
                }

                // Find next non-empty cell. Compare its value with index.
                // If values match, add it to index. Set indexFound to false.
                // Otherwise move this cell to the left of index and index this cell now.
                int j = index - 1;
                for (; j >= 0; j--) {
                    if (cells[i][j] != 0) {
                        if (cells[i][j] == cells[i][index]) {
                            cells[i][index] = cells[i][index] + cells[i][j];
                            cells[i][j] = 0;
                            indexFound = false;
                            count += (index - j);

                            // Add to score
                            score += cells[i][index];
                            break;
                        } else {
                            cells[i][index - 1] = cells[i][j];
                            if(j != index - 1) cells[i][j] = 0;
                            indexFound = true;
                            index = index - 1;
                            count += (index - j);
                            break;
                        }
                    }
                }
                if(j == -1) break outer;
            }
        }
        return count;
    }


    // Move cells left. Returns number of cells moved.
    public int moveCellsLeft() {
        // count: count of number of cells moved.
        int count = 0;
        // index: a placeholder for remembering cell.
        int index;
        // indexFound: indicates if 'index' is already found. Initialized to false.
        boolean indexFound = false;

        // For every row (from top to bottom),
        for (int i=0; i<cells.length; i++) {

            indexFound = false;
            // It is initialized to one cell left off the leftmost cell.
            index = -1;

            outer: while (index < 4) {

                if (!indexFound) {
                    // Set index to one cell right off index.
                    index = index + 1;
                    indexFound = true;

                    // Start from index cell, go right. Move first non-empty cell to index place.
                    int j = index;
                    for (; j < cells[i].length; j++) {
                        if (cells[i][j] != 0) {
                            cells[i][index] = cells[i][j];
                            if(j != index) cells[i][j] = 0;
                            count += (j - index);
                            break;
                        }
                    }
                    if(j == cells[i].length) break outer;
                }

                // Find next non-empty cell. Compare its value with index.
                // If values match, add it to index. Set indexFound to false.
                // Otherwise move this cell to the right of index and index this cell now.
                int j = index + 1;
                for (; j < cells[i].length; j++) {
                    if (cells[i][j] != 0) {
                        if (cells[i][j] == cells[i][index]) {
                            cells[i][index] = cells[i][index] + cells[i][j];
                            cells[i][j] = 0;
                            indexFound = false;
                            count += (j - index);

                            //Add to score
                            score += cells[i][index];

                            break;
                        } else {
                            cells[i][index + 1] = cells[i][j];
                            if(j != index + 1) cells[i][j] = 0;
                            indexFound = true;
                            index = index + 1;
                            count += (j - index);
                            break;
                        }
                    }
                }
                if(j == cells[i].length) break outer;
            }
        }
        return count;
    }

    // Move cells up. Returns number of cells moved.
    public int moveCellsUp() {
        // count: count of number of cells moved.
        int count = 0;
        // index: a placeholder for remembering cell.
        int index;
        // indexFound: indicates if 'index' is already found. Initialized to false.
        boolean indexFound = false;

        // For every column (from left to right),
        for (int j=0; j<cells[0].length; j++) {

            indexFound = false;
            // It is initialized to one cell up off the topmost cell.
            index = -1;

            outer: while (index < 4) {

                if (!indexFound) {
                    // Set index to one cell below the index.
                    index = index + 1;
                    indexFound = true;

                    // Start from index cell, go down. Move first non-empty cell to index place.
                    int i = index;
                    for (; i < cells.length; i++) {
                        if (cells[i][j] != 0) {
                            cells[index][j] = cells[i][j];
                            if(i != index) cells[i][j] = 0;
                            count += (i - index);
                            break;
                        }
                    }
                    if(i == cells.length) break outer;
                }

                // Find next non-empty cell. Compare its value with index.
                // If values match, add it to index. Set indexFound to false.
                // Otherwise move this cell just below index and index this cell now.
                int i = index + 1;
                for (; i < cells.length; i++) {
                    if (cells[i][j] != 0) {
                        if (cells[i][j] == cells[index][j]) {
                            cells[index][j] = cells[index][j] + cells[i][j];
                            cells[i][j] = 0;
                            indexFound = false;
                            count += (i - index);

                            // Add to score
                            score += cells[index][j];

                            break;
                        } else {
                            cells[index + 1][j] = cells[i][j];
                            if(i != index + 1) cells[i][j] = 0;
                            indexFound = true;
                            index = index + 1;
                            count += (i - index);
                            break;
                        }
                    }
                }
                if(i == cells.length) break outer;
            }
        }
        return count;
    }

    // Move cells down. Returns number of cells moved.
    public int moveCellsDown() {
        // count: count of number of cells moved.
        int count = 0;
        // index: a placeholder for remembering cell.
        int index;
        // indexFound: indicates if 'index' is already found. Initialized to false.
        boolean indexFound = false;

        // For every column (from left to right),
        for (int j=0; j<cells[0].length; j++) {

            indexFound = false;
            // It is initialized to one cell up off the bottommost cell.
            index = 4;

            outer: while (index > 0) {

                if (!indexFound) {
                    // Set index to one cell above the index.
                    index = index - 1;
                    indexFound = true;

                    // Start from index cell, go up. Move first non-empty cell to index place.
                    int i = index;
                    for (; i > -1; i--) {
                        if (cells[i][j] != 0) {
                            cells[index][j] = cells[i][j];
                            if(i != index) cells[i][j] = 0;
                            count += (index - i);
                            break;
                        }
                    }
                    if(i == -1) break outer;
                }

                // Find next non-empty cell. Compare its value with index.
                // If values match, add it to index. Set indexFound to false.
                // Otherwise move this cell just above index and index this cell now.
                int i = index - 1;
                for (; i > -1; i--) {
                    if (cells[i][j] != 0) {
                        if (cells[i][j] == cells[index][j]) {
                            cells[index][j] = cells[index][j] + cells[i][j];
                            cells[i][j] = 0;
                            indexFound = false;
                            count += (index - i);

                            // Add to score
                            score += cells[index][j];

                            break;
                        } else {
                            cells[index - 1][j] = cells[i][j];
                            if(i != index-1) cells[i][j] = 0;
                            indexFound = true;
                            index = index - 1;
                            count += (index - i);
                            break;
                        }
                    }
                }
                if(i == -1) break outer;
            }
        }
        return count;
    }

    public boolean isGameOver() {
        if(countEmptyCells() > 0) return false;
        for (int i = 0; i < cells.length; i++) {
            for (int j = 1; j < cells[i].length; j++) {
                if(cells[i][j-1] == cells[i][j]) return false;
            }
        }
        for (int i = 1; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                if(cells[i-1][j] == cells[i][j]) return false;
            }
        }
        return true;
    }

    private int countEmptyCells() {
        int count = 0;

        for (int i=0; i<cells.length; i++) {
            for (int j=0; j<cells[i].length; j++) {
                if(cells[i][j] == 0) count++;
            }
        }

        return count;
    }

    static class Coords {
        int row, column;

        public Coords(int row, int column) {
            this.row = row;
            this.column = column;
        }
    }

}
