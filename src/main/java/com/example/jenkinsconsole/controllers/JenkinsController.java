package com.example.jenkinsconsole.controllers;

import com.example.jenkinsconsole.exception.JenkinsErrorMessage;
import com.example.jenkinsconsole.service.JenkinsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/api")
public class JenkinsController {

    private final JenkinsService jenkinsService;

    @Autowired
    public JenkinsController(JenkinsService jenkinsService) {
        this.jenkinsService = jenkinsService;
    }

    @GetMapping("/errors")
    public Set<JenkinsErrorMessage> getErrors() {
        return jenkinsService.getErrorMessages();
    }

}























