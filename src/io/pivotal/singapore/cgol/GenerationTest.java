package io.pivotal.singapore.cgol;

import org.junit.Test;

import static org.junit.Assert.*;

public class GenerationTest {
    @Test
    public void parsesSeed() {
        String seed = ".....\n" +
                "..#..\n" +
                "..#..\n" +
                "..#..\n" +
                ".....";

        Generation subject = new Generation(seed);

        assertEquals(".....\n" +
                "..#..\n" +
                "..#..\n" +
                "..#..\n" +
                ".....", subject.toString());
    }

    @Test
    public void blinker() {
        String seed = ".....\n" +
                      "..#..\n" +
                      "..#..\n" +
                      "..#..\n" +
                      ".....";

        assertEquals(".....\n" +
                     ".....\n" +
                     ".###.\n" +
                     ".....\n" +
                     ".....", new Generation(seed).tick().toString());

    }

    @Test
    public void block() {
        String seed = "....\n" +
                      ".##.\n" +
                      ".##.\n" +
                      "....";

        assertEquals(seed, new Generation(seed).tick().toString());
        assertEquals(seed, new Generation(seed).tick().tick().toString());
    }

    @Test
    public void beacon() {
        String seed = "......\n" +
                      ".##...\n" +
                      ".##...\n" +
                      "...##.\n" +
                      "...##.\n" +
                      "......";

        assertEquals(seed, new Generation(seed).tick().tick().toString());
    }

    @Test
    public void spaceship() {
        String seed = "#..#.\n" +
                      "....#\n" +
                      "#...#\n" +
                      ".####";

        String expected = "......\n" +
                          "...##.\n" +
                          ".##.##\n" +
                          ".####.\n" +
                          "..##..";

        assertEquals(expected, new Generation(seed).tick().toString());
    }

    @Test
    public void spaceship2ndGeneration() {
        String seed = "#..#.\n" +
                      "....#\n" +
                      "#...#\n" +
                      ".####";

        String expected = "......\n" +
                          "..####\n" +
                          ".#...#\n" +
                          ".....#\n" +
                          ".#..#.";

        assertEquals(expected, new Generation(seed).tick().tick().toString());
    }

    @Test
    public void spaceship3rdGeneration() {
        String seed = "#..#.\n" +
                      "....#\n" +
                      "#...#\n" +
                      ".####";

        String expected = "...##..\n" +
                          "..####.\n" +
                          "..##.##\n" +
                          "....##.\n" +
                          ".......";

        assertEquals(expected, new Generation(seed).tick().tick().tick().toString());
    }
}