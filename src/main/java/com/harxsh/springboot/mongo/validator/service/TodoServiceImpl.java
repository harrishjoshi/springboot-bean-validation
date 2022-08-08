package com.harxsh.springboot.mongo.validator.service;

import com.harxsh.springboot.mongo.validator.collection.Todo;
import com.harxsh.springboot.mongo.validator.exception.TodoException;
import com.harxsh.springboot.mongo.validator.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolationException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static com.harxsh.springboot.mongo.validator.util.StringUtil.isNotEmpty;

@Service
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService {

    private final TodoRepository todoRepository;

    @Override
    public void create(Todo todo) throws ConstraintViolationException, TodoException {
        String title = todo.getTitle();
        Optional<Todo> optionalTodo = todoRepository.findByTitle(title);
        if (optionalTodo.isPresent()) {
            throw new TodoException(TodoException.AlreadyExistsException(title));
        }

        todo.setCreatedAt(new Date());
        todoRepository.save(todo);
    }

    @Override
    public List<Todo> getAll() {
        return todoRepository.findAll();
    }

    @Override
    public Todo getById(String id) throws TodoException {
        Optional<Todo> todo = todoRepository.findById(id);
        if (todo.isPresent())
            return todo.get();
        else throw new TodoException(TodoException.NotFoundException(id));
    }

    @Override
    public void update(String id, Todo todo) throws ConstraintViolationException, TodoException {
        Optional<Todo> optionalTodo = todoRepository.findById(id);
        if (optionalTodo.isPresent()) {
            Todo updateTodo = optionalTodo.get();
            todo.setId(updateTodo.getId());
            todo.setCreatedAt(updateTodo.getCreatedAt());
            todo.setModifiedAt(new Date());
            todoRepository.save(todo);
        } else throw new TodoException(TodoException.NotFoundException(id));
    }

    @Override
    public void deleteById(String id) throws TodoException {
        Optional<Todo> optionalTodo = todoRepository.findById(id);
        if (optionalTodo.isPresent()) {
            todoRepository.deleteById(id);
        } else throw new TodoException(TodoException.NotFoundException(id));
    }
}
