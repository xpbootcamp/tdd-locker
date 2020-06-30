package com.tw;

import com.tw.robot.LockerRobot;

import java.util.List;

import static java.lang.String.format;

public class LockerRobotDirector {
    private static final String MANAGER_REPORT_LINE = "M %d %d";
    private static final String MANAGER_LOCKER_REPORT_LINE = "\n\tL %d %d";
    private static final String ROBOT_REPORT_LINE = "\n\tR %d %d";
    private static final String ROBOT_LOCKER_REPORT_LINE = "\n\t\tL %d %d";

    private final List<LockerRobotManager> managers;

    public LockerRobotDirector(List<LockerRobotManager> managers) {
        this.managers = managers;
    }

    public String generateReport() {
        StringBuilder builder = new StringBuilder();
        for (LockerRobotManager manager : managers) {
            builder.append(getManagerReport(manager));
        }
        return builder.toString().trim();
    }

    private String getManagerReport(LockerRobotManager firstManager) {
        StringBuilder stringBuilder = new StringBuilder(format(MANAGER_REPORT_LINE, firstManager.getAvailableCapacity(), firstManager.getCapacity()));

        for (Storable storable : firstManager.getStorables()) {
            if (storable instanceof LockerRobot) {
                stringBuilder.append(getRobotReport((LockerRobot) storable));

            } else {
                stringBuilder.append(format(MANAGER_LOCKER_REPORT_LINE, storable.getAvailableCapacity(), storable.getCapacity()));
            }
        }
        stringBuilder.append("\n");
        return stringBuilder.toString();
    }

    private String getRobotReport(LockerRobot robot) {
        StringBuilder builder = new StringBuilder();

        builder.append(format(ROBOT_REPORT_LINE, robot.getAvailableCapacity(), robot.getCapacity()));

        for (Locker locker : robot.getLockers()) {
            builder.append(format(ROBOT_LOCKER_REPORT_LINE, locker.getAvailableCapacity(), locker.getCapacity()));
        }
        return builder.toString();
    }

}
