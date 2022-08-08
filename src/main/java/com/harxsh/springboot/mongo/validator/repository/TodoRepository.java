package com.harxsh.springboot.mongo.validator.repository;

import com.harxsh.springboot.mongo.validator.collection.Todo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TodoRepository extends MongoRepository<Todo, String> {

    @Query("{'title': ?0}")
    Optional<Todo> findByTitle(String title);
}
