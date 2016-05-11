package io.pivotal.singapore.cgol;

public class Game {
    private Generation currentGeneration;
    private final Generation seed;

    public Game(String seedString) {
        seed = new Generation(seedString);
        currentGeneration = seed;
    }

    @Override
    public String toString() {
        return currentGeneration.toString();
    }

    public void tick() {
        currentGeneration = currentGeneration.tick();
    }
}
