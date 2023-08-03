package com.example.jenkinsconsole.entities;

import com.example.jenkinsconsole.exception.JenkinsErrorMessage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "error_messages")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorMessage {

    @Id
    private String id;
    private String message;
    private boolean isFromJenkins;

    public ErrorMessage(JenkinsErrorMessage jenkinsErrorMessage) {
        this.message = jenkinsErrorMessage.getMessage();
        this.isFromJenkins = jenkinsErrorMessage.isFromJenkins();
    }
}
