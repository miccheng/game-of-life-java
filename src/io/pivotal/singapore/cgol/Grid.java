package io.pivotal.singapore.cgol;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Grid {
    private final int colCount;
    private final int rowCount;
    Set<Coordinate> liveSets = new HashSet<>();

    public Grid(int numRow, int numCols) {
        rowCount = numRow;
        colCount = numCols;
    }

    public Grid() {
        this(3, 3);
    }

    public int getColCount() {
        return colCount;
    }

    public int getRowCount() {
        return rowCount;
    }

    public void setLiveCell(Coordinate c) {
        liveSets.add(c);
    }

    public int countLiveNeighours(Coordinate c) {
        AtomicInteger count = new AtomicInteger(0);
        int row = c.getRow();
        int column = c.getColumn();

        forEachCellFromTo(row - 1, column - 1, row + 1, column + 1, (alive, coord) -> {
            if (coord.getRow() == row && coord.getColumn() == column) { return; }
            if (alive) count.incrementAndGet();
        });

        return count.get();
    }

    public void forEachCell(CellConsumer consumer) {
        forEachCellFromTo(0, 0, rowCount - 1, colCount - 1, consumer);
    }

    public List<Coordinate> getLiveCellCoordinates() {
        List<Coordinate> liveCells = new ArrayList<>();
        liveCells.addAll(liveSets);

        return liveCells;
    }

    public List<Coordinate> getDeadCellCoordinates() {
        final List<Coordinate> coords = new ArrayList<>();

        forEachCell((alive, c) -> {
            if (!alive) coords.add(c);
        });

        return coords;
    }

    private void forEachCellFromTo(int fromRow, int fromCol, int toRow, int toCol, CellConsumer consumer) {
        for (int i = fromRow; i <= toRow; i++) {
            for (int j = fromCol; j <= toCol; j++) {
                consumer.apply(liveCellAt(i, j), new Coordinate(i, j));
            }
        }
    }

    private boolean liveCellAt(int row, int column) {
        return liveSets.contains(new Coordinate(row, column));
    }

}

