package com.tw;

import com.tw.exception.InvalidTicketException;
import com.tw.exception.LockerIsFullException;
import com.tw.model.Bag;
import com.tw.model.Ticket;

import java.util.HashMap;

public class Locker implements Storable, Reportable {
    private final int capacity;
    private int availableCapacity;
    private final HashMap<Ticket, Bag> savedBags = new HashMap<>();

    public Locker(int capacity) {
        this.capacity = capacity;
        this.availableCapacity = capacity;
    }

    @Override
    public Ticket save(Bag bag) {
        if (availableCapacity <= 0) {
            throw new LockerIsFullException();
        }
        Ticket ticket = new Ticket();
        savedBags.put(ticket, bag);
        availableCapacity--;
        return ticket;
    }

    @Override
    public Bag pickUp(Ticket ticket) {
        Bag bag = savedBags.remove(ticket);
        if (bag == null) {
            throw new InvalidTicketException();
        }
        return bag;
    }

    @Override
    public boolean isFull() {
        return availableCapacity == 0;
    }

    @Override
    public boolean contains(Ticket ticket) {
        return savedBags.containsKey(ticket);
    }

    @Override
    public int getAvailableCapacity() {
        return availableCapacity;
    }

    @Override
    public int getCapacity() {
        return capacity;
    }

    @Override
    public String report() {
        return String.format("L %d %d", availableCapacity, capacity);
    }
}
