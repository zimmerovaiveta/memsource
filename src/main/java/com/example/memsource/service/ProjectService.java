package com.example.memsource.service;

import com.example.memsource.dto.ProjectList;
import com.example.memsource.dto.UserCredentialsDto;
import com.example.memsource.dto.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class ProjectService {
    private final String LIST_PROJECTS_URL = "https://cloud.memsource.com/web/api2/v1/projects";

    @Autowired
    private RestTemplate restTemplate;

    public ProjectList listProjects(String authToken) {

        UriComponentsBuilder builder = UriComponentsBuilder
                .fromUriString(LIST_PROJECTS_URL)
                .queryParam("token", authToken);

        ResponseEntity<ProjectList> projects = restTemplate.getForEntity(builder.toUriString(), ProjectList.class);

        return projects.getBody();
    }
}
