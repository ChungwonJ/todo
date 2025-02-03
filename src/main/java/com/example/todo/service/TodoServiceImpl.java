package com.example.todo.service;

import com.example.todo.dto.TodoRequestDto;
import com.example.todo.dto.TodoResponseDto;
import com.example.todo.entity.Todo;
import com.example.todo.repository.TodoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class TodoServiceImpl implements TodoService {
    private final TodoRepository todoRepository;

    public TodoServiceImpl(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @Override
    public TodoResponseDto createTodo(TodoRequestDto todoRequestDto) {
        Todo todo = new Todo(todoRequestDto.getUsername(), todoRequestDto.getContents(), todoRequestDto.getPassword());
        return todoRepository.saveTodo(todo);
    }

    @Override
    public List<TodoResponseDto> findAllTodos() {
        return todoRepository.findAllTodos();
    }

    @Override
    public TodoResponseDto findTodoById(Long id) {
        Optional<TodoResponseDto> optionalTodo = todoRepository.findTodoById(id);
        return optionalTodo.orElse(null);
    }

    @Transactional
    @Override
    public TodoResponseDto updateContents(Long id, String username, String contents, String password, LocalDateTime date) {
        if (username == null || contents == null || password == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The title, content, and password are required values.");
        }

        LocalDateTime modifiedDate = LocalDateTime.now();

        int updatedRow = todoRepository.updateContents(id, username, contents, modifiedDate, password);

        if (updatedRow == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No data has been modified.");
        }

        return todoRepository.findTodoById(id).orElseThrow(() -> new NoSuchElementException("Todo not found with id: " + id));
    }

    public void deleteTodo(Long id, String password) {

        int deletedRow = todoRepository.deleteTodo(id,password);

        if (deletedRow == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id = " + id);
        }

    }
}
