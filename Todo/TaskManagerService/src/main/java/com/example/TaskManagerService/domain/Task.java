package com.example.TaskManagerService.domain;

import org.springframework.data.annotation.Id;

public class Task {
    @Id
    private String taskId;
    private String taskName;
    private String description;
    private String priority;

    public Task() {
    }

    public Task(String taskId, String taskName, String description, String priority) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.description = description;
        this.priority = priority;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    @Override
    public String toString() {
        return "Task{" + "taskId='" + taskId + '\'' + ", taskName='" + taskName + '\'' + ", description='" + description + '\'' + ", priority='" + priority + '\'' + '}';
    }
}
