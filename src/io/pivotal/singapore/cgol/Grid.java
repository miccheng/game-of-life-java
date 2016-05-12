package io.pivotal.singapore.cgol;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Grid {
    private int colCount;
    private int rowCount;
    private int originCol = 0;
    private int originRow = 0;

    Set<Coordinate> liveSets = new HashSet<>();

    public Grid(int numRow, int numCols, int originRow, int originCol) {
        this.rowCount = numRow;
        this.colCount = numCols;
        this.originRow = originRow;
        this.originCol = originCol;
    }

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

    public int getOriginCol() {
        return originCol;
    }

    public int getOriginRow() {
        return originRow;
    }

    public int getColumnWidth() {
        return colCount - originCol;
    }

    public void setLiveCell(Coordinate c) {
        liveSets.add(c);

        resizeGridFromCoordinate(c);
    }

    public int countLiveNeighours(Coordinate c) {
        AtomicInteger count = new AtomicInteger(0);

        forEachNeighbour(c, (alive, coord) -> {
            if (alive) count.incrementAndGet();
        });

        return count.get();
    }

    public void forEachCell(CellConsumer consumer) {
        forEachCellFromTo(originRow, originCol, rowCount - 1, colCount - 1, consumer);
    }

    public void forEachCandidateCell(CellConsumer consumer) {
        liveSets.forEach(c -> {
            consumer.apply(true, c);
            forEachNeighbour(c, (alive, coord) -> {
                if (!alive) consumer.apply(false, coord);
            });
        });
    }

    private void forEachNeighbour(Coordinate c, CellConsumer consumer) {
        int row = c.getRow();
        int column = c.getColumn();

        forEachCellFromTo(row - 1, column - 1, row + 1, column + 1, (alive, coord) -> {
            if (coord.getRow() == row && coord.getColumn() == column) { return; }
            consumer.apply(alive, coord);
        });
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

    private void resizeGridFromCoordinate(Coordinate c) {
        if (c.getRow() < originRow) originRow = c.getRow();
        if (c.getColumn() < originCol) originCol = c.getColumn();

        rowCount = Math.max(c.getRow() + 1, rowCount);
        colCount = Math.max(c.getColumn() + 1, colCount);
    }
}

