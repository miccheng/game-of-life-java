package io.pivotal.singapore.cgol;

public class Rules {
    private Rules() {}

    public static boolean apply(boolean alive, int neighbours) {
        if (!alive && neighbours == 3) return true;
        if (!alive) return false;

        if (neighbours < 2) return false;
        if (neighbours > 1 && neighbours < 4) return true;

        return false;
    }
}
