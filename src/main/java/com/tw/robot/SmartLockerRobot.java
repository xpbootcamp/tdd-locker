package com.tw.robot;

import com.tw.model.Bag;
import com.tw.Locker;
import com.tw.model.Ticket;

import java.util.List;

public class SmartLockerRobot extends LockerRobot {

    public SmartLockerRobot(List<Locker> lockers) {
        super(lockers);
    }

    public Ticket save(Bag bag) {
        lockers.sort((o1, o2) -> o2.getAvailableCapacity() - o1.getAvailableCapacity());
        return lockers.get(0).save(bag);
    }
}
