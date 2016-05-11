package io.pivotal.singapore.cgol;

import org.junit.Test;

import static org.junit.Assert.*;

public class RulesTest {
    @Test
    public void liveCellWithLessThan2NeighboursDies() {
        assertFalse(Rules.apply(true, 0));
        assertFalse(Rules.apply(true, 1));
    }

    @Test
    public void liveCellWith2or3NeighboursLives() {
        assertTrue(Rules.apply(true, 2));
        assertTrue(Rules.apply(true, 3));
    }

    @Test
    public void liveCellWithMoreThan3NeighboursDies() {
        assertFalse(Rules.apply(true, 4));
        assertFalse(Rules.apply(true, 5));
        assertFalse(Rules.apply(true, 6));
        assertFalse(Rules.apply(true, 7));
        assertFalse(Rules.apply(true, 8));
    }

    @Test
    public void deadCellWithExactly3NeighboursLives() {
        assertTrue(Rules.apply(false, 3));

        assertFalse(Rules.apply(false, 1));
        assertFalse(Rules.apply(false, 2));
        assertFalse(Rules.apply(false, 4));
        assertFalse(Rules.apply(false, 5));
        assertFalse(Rules.apply(false, 6));
        assertFalse(Rules.apply(false, 7));
        assertFalse(Rules.apply(false, 8));
    }
}