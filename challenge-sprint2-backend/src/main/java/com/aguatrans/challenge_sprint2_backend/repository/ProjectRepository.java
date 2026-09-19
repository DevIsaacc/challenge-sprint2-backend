package com.aguatrans.challenge_sprint2_backend.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.aguatrans.challenge_sprint2_backend.model.Project;

public interface ProjectRepository extends MongoRepository<Project, String> {
}