package io.pivotal.singapore.cgol;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.Assert.*;

public class GridTest {
    @Test
    public void countsLiveNeighboursForACellOnTheEdge() {
        Grid grid = new Grid();
        grid.setLiveCell(c(0, 1));
        grid.setLiveCell(c(1, 1));

        assertEquals(1, grid.countLiveNeighours(c(0, 1)));
    }

    @Test
    public void countLiveNeighboursForACellAtACorner() {
        Grid grid = new Grid();
        grid.setLiveCell(c(0, 1));
        grid.setLiveCell(c(1, 0));
        grid.setLiveCell(c(1, 1));

        assertEquals(3, grid.countLiveNeighours(c(0, 0)));
    }

    @Test
    public void countLiveNeighboursForACellWith8Neighbours() {
        Grid grid = new Grid();
        grid.setLiveCell(c(0, 0));
        grid.setLiveCell(c(0, 1));
        grid.setLiveCell(c(0, 2));
        grid.setLiveCell(c(1, 0));
        grid.setLiveCell(c(1, 2));
        grid.setLiveCell(c(2, 0));
        grid.setLiveCell(c(2, 1));
        grid.setLiveCell(c(2, 2));

        assertEquals(8, grid.countLiveNeighours(c(1, 1)));
    }

    @Test
    public void listsLiveCells() {
        Grid grid = new Grid();
        grid.setLiveCell(c(99, 10000));
        grid.setLiveCell(c(100, 10001));

        List<Coordinate> coordinates = grid.getLiveCellCoordinates();

        assertTrue(coordinates.contains(c(99, 10000)));
        assertTrue(coordinates.contains(c(100, 10001)));
    }

    @Test
    public void listsDeadCells() {
        Grid grid = new Grid(2, 2);

        List<Coordinate> coords = grid.getDeadCellCoordinates();

        List<Coordinate> expected = Arrays.asList(c(0, 0), c(0, 1), c(1, 0), c(1, 1));
        assertEquals(expected, coords);
    }

    @Test
    public void iteratesOverCellsByRow() {
        Grid grid = new Grid(2, 2);
        final List<Coordinate> coords = new ArrayList<>();

        grid.forEachCell((c, coord) -> coords.add(coord));

        List<Coordinate> expected = Arrays.asList(c(0, 0), c(0, 1), c(1, 0), c(1, 1));
        assertEquals(expected, coords);
    }

    private Coordinate c(int row, int col) {
        return new Coordinate(row, col);
    }
}