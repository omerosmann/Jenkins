package com.example.jenkinsconsole.controllers;

import com.example.jenkinsconsole.exception.JenkinsErrorMessage;
import com.example.jenkinsconsole.service.ErrorHandlingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/errorHandling")
public class ErrorHandlingController {

    private final ErrorHandlingService errorHandlingService;

    @Autowired
    public ErrorHandlingController(ErrorHandlingService errorHandlingService) {
        this.errorHandlingService = errorHandlingService;
    }

    @GetMapping("/errors")
    public List<JenkinsErrorMessage> getErrorMessages() {
        // MongoDB'den hata mesajlarını alın ve dışarıya verin
        return errorHandlingService.getErrorMessagesFromMongoDB();
    }
}
