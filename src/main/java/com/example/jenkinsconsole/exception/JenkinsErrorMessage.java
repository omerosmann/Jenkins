package com.example.jenkinsconsole.exception;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class JenkinsErrorMessage {
    private String message;
    private boolean isFromJenkins;
}
