package com.example.memsource.controller;

import com.example.memsource.dto.ProjectDto;
import com.example.memsource.dto.ProjectList;
import com.example.memsource.dto.UserProfile;
import com.example.memsource.repository.UserRepository;
import com.example.memsource.service.AuthenticationService;
import com.example.memsource.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Validated
public class ProjectController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private AuthenticationService authenticationService;

    @GetMapping("/listProjects")
    @ResponseBody
    public ProjectDto[] listProjects(@RequestParam("userId") long userId) {

        UserProfile profile = authenticationService.getUserProfile(userId);

        ProjectList projectList = projectService.listProjects(profile.getToken());
        return projectList.getProjects();
    }




}
