package com.tw.robot;

import com.tw.Bag;
import com.tw.Locker;
import com.tw.Reportable;
import com.tw.Storable;
import com.tw.Ticket;
import com.tw.exception.InvalidTicketException;

import java.util.List;

import static com.tw.Reportable.gatherReport;
import static java.lang.String.format;
import static java.util.stream.Collectors.toList;

public abstract class LockerRobot implements Storable, Reportable {

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

    @Override
    public String report() {
        List<Storable> lockers = this.lockers.stream().map(locker -> (Storable) locker).collect(toList());

        return gatherReport(format("R %d %d", getAvailableCapacity(), getCapacity()), lockers);
    }
}
