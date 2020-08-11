package com.tw.robot;

import com.tw.model.Bag;
import com.tw.Locker;
import com.tw.model.Ticket;
import com.tw.exception.InvalidTicketException;
import com.tw.exception.LockerIsFullException;
import org.junit.Test;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

public class SmartLockerRobotTest {
    @Test
    public void should_return_ticket_and_save_in_1st_locker_when_save_bag_given_robot_manage_two_lockers_1st_capacity_is_3_and_2nd_is_2() {
        Locker firstLocker = new Locker(3);
        SmartLockerRobot robot = new SmartLockerRobot(asList(firstLocker, new Locker(2)));

        Bag myBag = new Bag();
        Ticket ticket = robot.save(myBag);

        assertNotNull(ticket);
        assertSame(myBag, firstLocker.pickUp(ticket));
    }

    @Test
    public void should_return_ticket_and_save_in_2nd_locker_when_save_bag_given_robot_manage_two_lockers_1st_capacity_is_2_and_2nd_is_3() {
        Locker secondLocker = new Locker(3);
        SmartLockerRobot robot = new SmartLockerRobot(asList(new Locker(2), secondLocker));

        Bag myBag = new Bag();
        Ticket ticket = robot.save(myBag);

        assertNotNull(ticket);
        assertSame(myBag, secondLocker.pickUp(ticket));
    }

    @Test
    public void should_return_ticket_and_save_in_1st_locker_when_save_bag_given_robot_manage_two_lockers_1st_capacity_is_2_and_2nd_is_2() {
        Locker firstLocker = new Locker(2);
        SmartLockerRobot robot = new SmartLockerRobot(asList(firstLocker, new Locker(2)));

        Bag myBag = new Bag();
        Ticket ticket = robot.save(myBag);

        assertNotNull(ticket);
        assertSame(myBag, firstLocker.pickUp(ticket));
    }

    @Test(expected = LockerIsFullException.class)
    public void should_throw_LockerIsFullException_when_save_bag_given_robot_manage_two_lockers_1st_capacity_is_0_and_2nd_is_0() {
        SmartLockerRobot robot = new SmartLockerRobot(asList(new Locker(0), new Locker(0)));

        robot.save(new Bag());
    }

    @Test
    public void should_return_bag_when_pickup_bag_given_valid_ticket() {
        SmartLockerRobot robot = new SmartLockerRobot(asList(new Locker(2), new Locker(3)));
        Bag myBag = new Bag();
        Ticket ticket = robot.save(myBag);

        Bag bag = robot.pickUp(ticket);

        assertSame(myBag, bag);
    }

    @Test(expected = InvalidTicketException.class)
    public void should_throw_InvalidTicketException_when_pickup_bag_given_invalid_ticket() {
        SmartLockerRobot robot = new SmartLockerRobot(asList(new Locker(2), new Locker(3)));

        robot.pickUp(new Ticket());
    }
}
