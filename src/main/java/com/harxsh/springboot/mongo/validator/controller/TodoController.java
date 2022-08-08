package com.harxsh.springboot.mongo.validator.controller;

import com.harxsh.springboot.mongo.validator.collection.Todo;
import com.harxsh.springboot.mongo.validator.exception.TodoException;
import com.harxsh.springboot.mongo.validator.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import java.util.List;

@RestController
@RequestMapping("todo")
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    @GetMapping("/all")
    public ResponseEntity<?> getAllTodos() {
        List<Todo> todos = todoService.getAll();
        boolean isEmpty = todos.isEmpty();

        return new ResponseEntity<>(
                isEmpty ? "No data available." : todos,
                isEmpty ? HttpStatus.NOT_FOUND : HttpStatus.OK
        );
    }

    @PostMapping("create")
    public ResponseEntity<?> create(@RequestBody Todo todo) {
        try {
            todoService.create(todo);
            return new ResponseEntity<>(todo, HttpStatus.OK);
        } catch (ConstraintViolationException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        } catch (TodoException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getTodoById(@PathVariable String id) {
        try {
            return new ResponseEntity<>(todoService.getById(id), HttpStatus.OK);
        } catch (TodoException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("{id}/update")
    public ResponseEntity<?> updateById(@PathVariable String id, @RequestBody Todo todo) {
        try {
            todoService.update(id, todo);
            return new ResponseEntity<>("Todo with id " + id + " updated successfully.", HttpStatus.OK);
        } catch (ConstraintViolationException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        } catch (TodoException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @DeleteMapping("{id}/delete")
    public ResponseEntity<?> deleteById(@PathVariable String id) {
        try {
            todoService.deleteById(id);
            return new ResponseEntity<>("Todo with id " + id + " deleted successfully.", HttpStatus.OK);
        } catch (TodoException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}