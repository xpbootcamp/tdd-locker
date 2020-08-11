package com.tw;

import com.tw.exception.InvalidTicketException;
import com.tw.exception.LockerIsFullException;
import com.tw.model.Bag;
import com.tw.model.Ticket;
import com.tw.robot.LockerRobot;

import java.util.ArrayList;
import java.util.List;

import static com.tw.Reportable.gatherReport;
import static java.lang.String.format;

public class LockerRobotManager implements Reportable {

    private List<Storable> storables = new ArrayList<>();

    public LockerRobotManager(List<Storable> input) {
        List<Storable> storables = new ArrayList<>(input);
        for (Storable storable : input) {
            if (storable instanceof LockerRobot) {
                this.storables.add(storable);
                storables.remove(storable);
            }
        }
        this.storables.addAll(storables);
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
        return gatherReport(format("M %d %d", getAvailableCapacity(), getCapacity()), storables);
    }

    private int getAvailableCapacity() {
        return storables.stream().mapToInt(Storable::getAvailableCapacity).sum();
    }

    private int getCapacity() {
        return storables.stream().mapToInt(Storable::getCapacity).sum();
    }
}
