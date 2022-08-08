package com.harxsh.springboot.mongo.validator.service;

import com.harxsh.springboot.mongo.validator.collection.Todo;
import com.harxsh.springboot.mongo.validator.exception.TodoException;

import javax.validation.ConstraintViolationException;
import java.util.List;

public interface TodoService {

    void create(Todo todo) throws ConstraintViolationException, TodoException;

    List<Todo> getAll();

    Todo getById(String id) throws TodoException;

    void update(String id, Todo todo) throws ConstraintViolationException, TodoException;

    void deleteById(String id) throws TodoException;
}
