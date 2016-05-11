package io.pivotal.singapore.cgol;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Generation {
    private static final char ALIVE_CHAR = '#';
    private static final char DEAD_CHAR = '.';
    private Grid grid;

    public Generation(String seed) {
        String[] rows = seed.split("\\n");

        grid = new Grid(rows.length, rows[0].length());

        for (int i = 0; i < rows.length; i++) {
            char[] row = rows[i].toCharArray();

            for (int j = 0; j < row.length; j++) {
                if (row[j] == ALIVE_CHAR) {
                    grid.setLiveCell(new Coordinate(i, j));
                }
            }
        }
        System.out.println("");
    }

    public Generation(Grid newGrid) {
        this.grid = newGrid;
    }

    @Override
    public String toString() {
        StringBuffer returnString = new StringBuffer();

        grid.forEachCell((alive, c) -> {
            if (c.getRow() > 0 && c.getColumn() == 0) returnString.append("\n");
            returnString.append(alive ? ALIVE_CHAR : DEAD_CHAR);
        });

        return returnString.toString();
    }

    public Generation tick() {
        final Grid newGrid = new Grid(grid.getRowCount(), grid.getColCount());

        grid.forEachCandidateCell((alive, c) -> {
            int numNeighbours = grid.countLiveNeighours(c);
            if (Rules.apply(alive, numNeighbours)) {
                newGrid.setLiveCell(c);
            }
        });

        return new Generation(newGrid);
    }
}
