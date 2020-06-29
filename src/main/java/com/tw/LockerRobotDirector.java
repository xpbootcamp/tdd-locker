package com.tw;

import java.util.List;

public class LockerRobotDirector {
    private final List<LockerRobotManager> managers;

    public LockerRobotDirector(List<LockerRobotManager> managers) {
        this.managers = managers;
    }

    public String generateReport() {
        LockerRobotManager firstManager = managers.get(0);

        StringBuilder stringBuilder = new StringBuilder(String.format("M %d %d", firstManager.getAvailableCapacity(), firstManager.getCapacity()));
        for (Storable storable : firstManager.getStorables()) {
            stringBuilder.append(String.format("\n\tL %d %d", storable.getAvailableCapacity(), storable.getCapacity()));
        }
        return stringBuilder.toString();
    }
}
