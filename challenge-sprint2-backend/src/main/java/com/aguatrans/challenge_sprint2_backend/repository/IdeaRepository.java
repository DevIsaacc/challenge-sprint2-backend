package com.aguatrans.challenge_sprint2_backend.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.aguatrans.challenge_sprint2_backend.model.Idea;

public interface IdeaRepository extends MongoRepository<Idea, String> {
    List<Idea> findByAutorId(String autorId);
}