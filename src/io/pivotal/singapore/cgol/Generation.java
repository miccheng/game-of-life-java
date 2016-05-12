package io.pivotal.singapore.cgol;

import java.util.concurrent.atomic.AtomicInteger;

public class Generation {
    private static final char ALIVE_CHAR = '#';
    private static final char DEAD_CHAR = '.';
    private static final char ROW_DELIMITER = '\n';
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
        AtomicInteger columnCounter = new AtomicInteger();

        grid.forEachCell((alive, c) -> {
            returnString.append(alive ? ALIVE_CHAR : DEAD_CHAR);

            if (columnCounter.incrementAndGet() == grid.getColumnWidth()) {
                returnString.append(ROW_DELIMITER);
                columnCounter.set(0);
            }
        });

        return returnString.toString().trim();
    }

    public Generation tick() {
        final Grid newGrid = new Grid(grid.getRowCount(), grid.getColCount(), grid.getOriginRow(), grid.getOriginCol());

        grid.forEachCandidateCell((alive, c) -> {
            int numNeighbours = grid.countLiveNeighours(c);
            if (Rules.apply(alive, numNeighbours)) {
                newGrid.setLiveCell(c);
            }
        });

        return new Generation(newGrid);
    }
}
