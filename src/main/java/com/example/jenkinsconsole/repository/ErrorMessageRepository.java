package com.example.jenkinsconsole.repository;

import com.example.jenkinsconsole.entities.ErrorMessage;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ErrorMessageRepository extends MongoRepository<ErrorMessage,String> {
}
