package com.tw;

import com.tw.exception.InvalidTicketException;
import com.tw.exception.LockerIsFullException;
import com.tw.robot.PrimaryLockerRobot;
import com.tw.robot.SmartLockerRobot;
import org.junit.Test;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

public class LockerRobotManagerTest {
    @Test
    public void should_return_ticket_save_in_1st_locker_when_save_bag_given_manager_has_two_lockers_with_capacity_and_no_robots() {
        Locker firstLocker = new Locker(1);
        LockerRobotManager manager = new LockerRobotManager(asList(firstLocker, new Locker(2)));

        Bag myBag = new Bag();
        Ticket ticket = manager.save(myBag);

        assertNotNull(ticket);
        assertSame(myBag, firstLocker.pickUp(ticket));
    }

    @Test
    public void should_return_ticket_save_in_2nd_locker_when_save_bag_given_manager_has_two_lockers_1st_is_full_2nd_has_capacity_and_no_robots() {
        Locker firstLocker = new Locker(1);
        Locker secondLocker = new Locker(2);
        LockerRobotManager manager = new LockerRobotManager(asList(firstLocker, secondLocker));
        firstLocker.save(new Bag());

        Bag myBag = new Bag();
        Ticket ticket = manager.save(myBag);

        assertNotNull(ticket);
        assertSame(myBag, secondLocker.pickUp(ticket));
    }

    @Test(expected = LockerIsFullException.class)
    public void should_throw_LockerIsFullException_when_save_bag_given_manager_has_two_lockers_both_are_full_and_no_robots() {
        Locker firstLocker = new Locker(1);
        Locker secondLocker = new Locker(1);
        LockerRobotManager manager = new LockerRobotManager(asList(firstLocker, secondLocker));
        firstLocker.save(new Bag());
        secondLocker.save(new Bag());

        manager.save(new Bag());
    }

    @Test
    public void should_return_ticket_and_save_to_1st_robots_when_save_bag_given_manager_has_two_robots_with_capacity_and_no_lockers() {
        Locker firstRobotLocker = new Locker(1);
        LockerRobotManager manager = new LockerRobotManager(asList(new PrimaryLockerRobot(singletonList(firstRobotLocker)),
                new PrimaryLockerRobot(singletonList(new Locker(2)))));

        Bag myBag = new Bag();
        Ticket ticket = manager.save(myBag);

        assertNotNull(ticket);
        assertSame(myBag, firstRobotLocker.pickUp(ticket));
    }

    @Test
    public void should_return_ticket_and_save_to_2nd_robot_when_save_bag_given_manager_has_two_robots_1st_is_full_2nd_has_capacity_and_no_lockers() {
        Locker firstRobotLocker = new Locker(1);
        Locker secondRobotLocker = new Locker(2);
        LockerRobotManager manager = new LockerRobotManager(asList(new SmartLockerRobot(singletonList(firstRobotLocker)),
                new SmartLockerRobot(singletonList(secondRobotLocker))));

        firstRobotLocker.save(new Bag());

        Bag myBag = new Bag();
        Ticket ticket = manager.save(myBag);

        assertNotNull(ticket);
        assertSame(myBag, secondRobotLocker.pickUp(ticket));
    }

    @Test(expected = LockerIsFullException.class)
    public void should_throw_LockerIsFullException_when_save_bag_given_manager_has_two_robots_both_are_full_and_no_lockers() {
        Locker firstRobotLocker = new Locker(1);
        Locker secondRobotLocker = new Locker(1);
        LockerRobotManager manager = new LockerRobotManager(asList(new SmartLockerRobot(singletonList(firstRobotLocker)),
                new SmartLockerRobot(singletonList(secondRobotLocker))));
        firstRobotLocker.save(new Bag());
        secondRobotLocker.save(new Bag());

        manager.save(new Bag());
    }

    @Test
    public void should_return_ticket_and_save_to_robot_when_save_bag_given_manager_has_a_robot_and_a_locker_both_have_capacity() {
        Locker robotLocker = new Locker(2);
        LockerRobotManager manager = new LockerRobotManager(asList(new PrimaryLockerRobot(singletonList(robotLocker)), new Locker(3)));

        Bag myBag = new Bag();
        Ticket ticket = manager.save(myBag);

        assertNotNull(ticket);
        assertSame(myBag, robotLocker.pickUp(ticket));
    }

    @Test
    public void should_return_ticket_and_save_to_locker_when_save_bag_given_manager_has_a_robot_and_is_full_and_a_locker_with_capacity() {
        Locker robotLocker = new Locker(1);
        Locker managerLocker = new Locker(3);
        LockerRobotManager manager = new LockerRobotManager(asList(new PrimaryLockerRobot(singletonList(robotLocker)), managerLocker));
        robotLocker.save(new Bag());

        Bag myBag = new Bag();
        Ticket ticket = manager.save(myBag);

        assertNotNull(ticket);
        assertSame(myBag, managerLocker.pickUp(ticket));
    }

    @Test(expected = LockerIsFullException.class)
    public void should_throw_LockerIsFullException_when_save_bag_given_manager_has_a_robot_and_a_locker_both_are_full() {
        Locker robotLocker = new Locker(1);
        Locker managerLocker = new Locker(1);
        LockerRobotManager manager = new LockerRobotManager(asList(new PrimaryLockerRobot(singletonList(robotLocker)), managerLocker));
        robotLocker.save(new Bag());
        managerLocker.save(new Bag());

        manager.save(new Bag());
    }

    @Test
    public void should_return_bag_when_pickup_bag_given_manager_has_two_lockers_and_no_robots_and_ticket_is_valid() {
        LockerRobotManager manager = new LockerRobotManager(asList(new Locker(2), new Locker(5)));
        Bag myBag = new Bag();
        Ticket ticket = manager.save(myBag);

        Bag bag = manager.pickUp(ticket);

        assertSame(myBag, bag);
    }

    @Test
    public void should_return_bag_from_2nd_locker_when_pickup_bag_given_manager_has_two_lockers_and_no_robots_and_ticket_is_valid() {
        Locker firstLocker = new Locker(1);
        LockerRobotManager manager = new LockerRobotManager(asList(firstLocker, new Locker(5)));
        firstLocker.save(new Bag());
        Bag myBag = new Bag();
        Ticket ticket = manager.save(myBag);

        Bag bag = manager.pickUp(ticket);

        assertSame(myBag, bag);
    }

    @Test(expected = InvalidTicketException.class)
    public void should_throw_InvalidTicketException_when_pickup_bag_given_manager_has_two_lockers_and_no_robots_and_ticket_is_invalid() {
        LockerRobotManager manager = new LockerRobotManager(asList(new Locker(2), new Locker(5)));

        manager.pickUp(new Ticket());
    }

    @Test
    public void should_return_bag_when_pickup_bag_given_manager_has_two_robots_and_no_lockers_and_ticket_is_valid() {
        LockerRobotManager manager = new LockerRobotManager(asList(new PrimaryLockerRobot(singletonList(new Locker(1))),
                new SmartLockerRobot(singletonList(new Locker(2)))));
        Bag myBag = new Bag();
        Ticket ticket = manager.save(myBag);

        Bag bag = manager.pickUp(ticket);

        assertSame(myBag, bag);
    }

    @Test(expected = InvalidTicketException.class)
    public void should_throw_InvalidTicketException_when_pickup_bag_given_manager_has_two_robots_and_no_lockers_and_ticket_is_invalid() {
        LockerRobotManager manager = new LockerRobotManager(asList(new PrimaryLockerRobot(singletonList(new Locker(1))),
                new SmartLockerRobot(singletonList(new Locker(2)))));

        manager.pickUp(new Ticket());
    }


    @Test
    public void should_return_bag_when_pickup_bag_given_manager_has_a_robot_and_a_locker_and_ticket_is_valid() {
        LockerRobotManager manager = new LockerRobotManager(asList(new PrimaryLockerRobot(singletonList(new Locker(1))),
                new Locker(2)));
        Bag myBag = new Bag();
        Ticket ticket = manager.save(myBag);

        Bag bag = manager.pickUp(ticket);

        assertSame(myBag, bag);
    }

    @Test(expected = InvalidTicketException.class)
    public void should_throw_InvalidTicketException_when_pickup_bag_given_manager_has_a_robot_and_a_locker_and_ticket_is_invalid() {
        LockerRobotManager manager = new LockerRobotManager(asList(new PrimaryLockerRobot(singletonList(new Locker(1))),
                new Locker(2)));

        manager.pickUp(new Ticket());
    }
}
