package com.example.todo.repository;

import com.example.todo.dto.TodoResponseDto;
import com.example.todo.entity.Todo;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface TodoRepository {
    TodoResponseDto saveTodo(Todo todo);
    List<TodoResponseDto> findAllTodos();
    Optional<TodoResponseDto> findTodoById(Long id);
    int updateContents(Long id, String username, String contents, LocalDateTime modifiedDate, String password);

    int deleteTodo(Long id, String password);
}
