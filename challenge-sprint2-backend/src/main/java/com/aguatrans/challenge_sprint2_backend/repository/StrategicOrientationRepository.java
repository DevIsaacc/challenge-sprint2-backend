package com.aguatrans.challenge_sprint2_backend.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.aguatrans.challenge_sprint2_backend.model.StrategicOrientation;

public interface StrategicOrientationRepository extends MongoRepository<StrategicOrientation, String> {
}