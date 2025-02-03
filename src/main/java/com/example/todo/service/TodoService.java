package com.example.todo.service;

import com.example.todo.dto.TodoRequestDto;
import com.example.todo.dto.TodoResponseDto;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

public interface TodoService {
    TodoResponseDto createTodo(TodoRequestDto todoRequestDto);
    List<TodoResponseDto> findAllTodos();
    TodoResponseDto findTodoById(Long id);

    @Transactional
    TodoResponseDto updateContents(Long id, String username, String contents, String password, LocalDateTime date);

    void deleteTodo(Long id, String password);
}
