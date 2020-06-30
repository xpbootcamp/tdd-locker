package com.tw;

import java.util.List;
import java.util.stream.Collectors;

public class LockerRobotDirector {

    private final List<LockerRobotManager> managers;

    public LockerRobotDirector(List<LockerRobotManager> managers) {
        this.managers = managers;
    }

    public String generateReport() {
        return managers.stream().map(LockerRobotManager::report)
                .collect(Collectors.joining("\n"));
    }
}
