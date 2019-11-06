package com.example.memsource.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProjectList {
    @JsonProperty("content")
    private ProjectDto[] projects;

    public ProjectDto[] getProjects() {
        return projects;
    }

    public void setProjects(ProjectDto[] projects) {
        this.projects = projects;
    }
}
