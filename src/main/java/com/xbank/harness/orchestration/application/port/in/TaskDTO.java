package com.xbank.harness.orchestration.application.port.in;

import java.io.Serializable;

public class TaskDTO implements Serializable {
    private String taskId;
    private String description;
    private String status;
    private String relatedPrd;

    public TaskDTO() {}

    public TaskDTO(String taskId, String description, String relatedPrd) {
        this.taskId = taskId;
        this.description = description;
        this.relatedPrd = relatedPrd;
        this.status = "PENDING";
    }

    public String getTaskId() { return taskId; }
    public void setTaskId(String taskId) { this.taskId = taskId; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getRelatedPrd() { return relatedPrd; }
    public void setRelatedPrd(String relatedPrd) { this.relatedPrd = relatedPrd; }
}
