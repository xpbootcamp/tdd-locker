package com.tw;

public interface Storable {
    Ticket save(Bag bag);

    Bag pickUp(Ticket ticket);

    boolean isFull();

    boolean contains(Ticket ticket);

    int getAvailableCapacity();

    int getCapacity();
}
