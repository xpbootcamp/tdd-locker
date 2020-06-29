package com.tw.robot;

import com.tw.Bag;
import com.tw.Locker;
import com.tw.Storable;
import com.tw.Ticket;
import com.tw.exception.InvalidTicketException;

import java.util.List;
import java.util.function.Predicate;

public abstract class LockerRobot implements Storable {
    final List<Locker> lockers;

    LockerRobot(List<Locker> lockers) {
        this.lockers = lockers;
    }

    @Override
    public abstract Ticket save(Bag bag);

    @Override
    public Bag pickUp(Ticket ticket) {
        for (Locker locker : lockers) {
            if (locker.contains(ticket)) {
                return locker.pickUp(ticket);
            }
        }
        throw new InvalidTicketException();
    }

    @Override
    public boolean isFull() {
        return lockers.stream().allMatch(Locker::isFull);
    }

    @Override
    public boolean contains(Ticket ticket) {
        return lockers.stream().anyMatch(locker -> locker.contains(ticket));
    }

    @Override
    public int getAvailableCapacity() {
        return lockers.stream().mapToInt(Locker::getAvailableCapacity).sum();
    }

    @Override
    public int getCapacity() {
        return lockers.stream().mapToInt(Locker::getCapacity).sum();
    }
}
