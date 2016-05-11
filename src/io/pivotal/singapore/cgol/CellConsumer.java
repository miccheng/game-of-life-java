package io.pivotal.singapore.cgol;

@FunctionalInterface
interface CellConsumer {
    public void apply(boolean state, Coordinate c);
}
