package io.pivotal.singapore.cgol;

import org.junit.Test;

import static org.junit.Assert.*;

public class GameTest {
    @Test
    public void parsesSeed() {
        String seed = ".....\n" +
                      "..#..\n" +
                      "..#..\n" +
                      "..#..\n" +
                      ".....";

        Game subject = new Game(seed);

        assertEquals(subject.toString(), ".....\n" +
                                         "..#..\n" +
                                         "..#..\n" +
                                         "..#..\n" +
                                         ".....");
    }

    @Test
    public void ticks() {
        String seed = ".....\n" +
                "..#..\n" +
                "..#..\n" +
                "..#..\n" +
                ".....";

        Game subject = new Game(seed);
        subject.tick();

        assertEquals(subject.toString(), ".....\n" +
                ".....\n" +
                ".###.\n" +
                ".....\n" +
                ".....");

    }
}