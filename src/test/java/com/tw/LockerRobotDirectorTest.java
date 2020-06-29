package com.tw;

import com.tw.robot.PrimaryLockerRobot;
import com.tw.robot.SmartLockerRobot;
import org.junit.Test;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.junit.Assert.assertEquals;

public class LockerRobotDirectorTest {
    @Test
    public void should_return_M_3_13_L_0_8_L_3_5_when_generate_report_given_director_has_a_manager_with_two_lockers_1st_0_8_2nd_3_5() {
        Locker firstLocker = new Locker(8);
        Locker secondLocker = new Locker(5);
        LockerRobotDirector director = new LockerRobotDirector(singletonList(new LockerRobotManager(asList(firstLocker, secondLocker))));

        saveBags(firstLocker, 8);
        saveBags(secondLocker, 2);

        String report = director.generateReport();

        assertEquals("M 3 13\n\tL 0 8\n\tL 3 5", report);
    }

    @Test
    public void should_return_M_4_13_L_1_8_L_3_5_when_generate_report_given_director_has_a_manager_with_two_lockers_1st_1_8_2nd_3_5() {
        Locker firstLocker = new Locker(8);
        Locker secondLocker = new Locker(5);
        LockerRobotDirector director = new LockerRobotDirector(singletonList(new LockerRobotManager(asList(firstLocker, secondLocker))));

        saveBags(firstLocker, 7);
        saveBags(secondLocker, 2);

        String report = director.generateReport();

        assertEquals("M 4 13\n\tL 1 8\n\tL 3 5", report);
    }

    @Test
    public void should_return_M_3_10_R_1_5_L15_L2_5_when_generate_report_given_one_manager_has_a_robot_with_a_locker_1_5_and_a_locker_2_5() {
        Locker robotLocker = new Locker(5);
        Locker managerLocker = new Locker(5);
        LockerRobotDirector director = new LockerRobotDirector(singletonList(new LockerRobotManager(
                asList(new PrimaryLockerRobot(singletonList(robotLocker)), managerLocker))));

        saveBags(robotLocker, 4);
        saveBags(managerLocker, 3);

        String report = director.generateReport();

        assertEquals("M 3 10\n\tR 1 5\n\t\tL 1 5\n\tL 2 5", report);
    }

    @Test
    public void should_return_M_5_13_R_3_9_L13_L26_R_2_4_L24_when_generate_report_given_one_manager_has_two_robots_1st_with_two_lockers_13_26_2nd_with_one_locker_24() {
        Locker primaryFirstLocker = new Locker(3);
        Locker primarySecondLocker = new Locker(6);
        Locker smartRobotLocker = new Locker(4);

        LockerRobotDirector director = new LockerRobotDirector(singletonList(new LockerRobotManager(
                asList(new PrimaryLockerRobot(asList(primaryFirstLocker, primarySecondLocker)),
                        new SmartLockerRobot(singletonList(smartRobotLocker))))));

        saveBags(primaryFirstLocker, 2);
        saveBags(primarySecondLocker, 4);
        saveBags(smartRobotLocker, 2);

        String report = director.generateReport();

        assertEquals("M 5 13\n\tR 3 9\n\t\tL 1 3\n\t\tL 2 6\n\tR 2 4\n\t\tL 2 4", report);
    }

    private void saveBags(Locker locker, int times) {
        for (int i = 0; i < times; i++) {
            locker.save(new Bag());
        }
    }
}
