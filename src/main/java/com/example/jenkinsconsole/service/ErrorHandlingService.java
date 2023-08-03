package com.example.jenkinsconsole.service;

import com.example.jenkinsconsole.exception.JenkinsErrorMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ErrorHandlingService {

    private final MongoTemplate mongoTemplate;

    @Autowired
    public ErrorHandlingService(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public List<JenkinsErrorMessage> getErrorMessagesFromMongoDB() {
        // "error_messages" koleksiyonundan hata mesajlarını alın
        return mongoTemplate.findAll(JenkinsErrorMessage.class, "error_messages");
    }


}