package com.tw;

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

    private void saveBags(Locker locker, int times) {
        for (int i = 0; i < times; i++) {
            locker.save(new Bag());
        }
    }
}
