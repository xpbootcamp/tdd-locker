package com.tw;

public interface Storable extends Reportable {
    Ticket save(Bag bag);

    Bag pickUp(Ticket ticket);

    boolean isFull();

    boolean contains(Ticket ticket);

    int getAvailableCapacity();

    int getCapacity();
}
