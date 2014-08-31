package org.openhab.binding.vera.luup.config;

import java.util.List;

public class StartupStatus {
    private List<Object> tasks;

    public List<Object> getTasks() {
        return tasks;
    }

    public void setTasks(List<Object> tasks) {
        this.tasks = tasks;
    }
}
