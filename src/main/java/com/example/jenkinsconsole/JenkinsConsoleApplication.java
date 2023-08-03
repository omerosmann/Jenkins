package com.example.jenkinsconsole;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling // Scheduled görevlerin etkinleştirilmesi
@SpringBootApplication
public class JenkinsConsoleApplication {
    public static void main(String[] args) {
        SpringApplication.run(JenkinsConsoleApplication.class, args);
    }
}










