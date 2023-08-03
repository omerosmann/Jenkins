package com.example.jenkinsconsole.service;

import com.example.jenkinsconsole.entities.ErrorMessage;
import com.example.jenkinsconsole.repository.ErrorMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccessService {

    private final ErrorMessageRepository mongoErrorMessageRepository;

    @Autowired
    public AccessService(ErrorMessageRepository mongoErrorMessageRepository) {
        this.mongoErrorMessageRepository = mongoErrorMessageRepository;
    }

    public List<ErrorMessage> getAllErrorMessages() {
        return mongoErrorMessageRepository.findAll();
    }


}
