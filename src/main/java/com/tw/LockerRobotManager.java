package com.tw;

import com.tw.exception.InvalidTicketException;
import com.tw.exception.LockerIsFullException;

import java.util.List;

import static com.tw.util.IndentUtil.indent;
import static java.lang.String.format;

public class LockerRobotManager implements Reportable {

    private final List<Storable> storables;

    public LockerRobotManager(List<Storable> storables) {
        this.storables = storables;
    }

    public Ticket save(Bag bag) {
        for (Storable storable : storables) {
            if (!storable.isFull()) {
                return storable.save(bag);
            }
        }

        throw new LockerIsFullException();
    }

    public Bag pickUp(Ticket ticket) {
        for (Storable storable : storables) {
            if (storable.contains(ticket)) {
                return storable.pickUp(ticket);
            }
        }
        throw new InvalidTicketException();
    }

    @Override
    public String report() {
        StringBuilder stringBuilder = new StringBuilder(format("M %d %d", getAvailableCapacity(), getCapacity()));

        for (Storable storable : storables) {
            stringBuilder.append(format("\n%s", indent(storable.report())));
        }
        return stringBuilder.toString();
    }

    private int getAvailableCapacity() {
        return storables.stream().mapToInt(Storable::getAvailableCapacity).sum();
    }

    private int getCapacity() {
        return storables.stream().mapToInt(Storable::getCapacity).sum();
    }
}
