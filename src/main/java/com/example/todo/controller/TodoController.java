package com.example.todo.controller;

import com.example.todo.dto.TodoRequestDto;
import com.example.todo.dto.TodoResponseDto;
import com.example.todo.dto.TodoUpdateRequestDto;
import com.example.todo.entity.Todo;
import com.example.todo.service.TodoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/todos")
public class TodoController {
    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @PostMapping
    public ResponseEntity<TodoResponseDto> createTodo(@RequestBody TodoRequestDto todoRequestDto) {
        TodoResponseDto responseDto = todoService.createTodo(todoRequestDto);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @GetMapping
    public List<TodoResponseDto> findAllTodos() {
        return todoService.findAllTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TodoResponseDto> findTodoById(@PathVariable Long id) {
        return ResponseEntity.ok(todoService.findTodoById(id));
    }
    @PutMapping("/{id}")
    public ResponseEntity<TodoResponseDto> updateMemo(
            @PathVariable Long id,
            @RequestBody TodoUpdateRequestDto requestDto
    ) {

        TodoResponseDto updatedTodo = todoService.updateContents(
                id,
                requestDto.getUsername(),
                requestDto.getContents(),
                requestDto.getPassword(),
                LocalDateTime.now()
        );

        return new ResponseEntity<>(updatedTodo, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable Long id, @RequestBody Map<String, String> body) {
        String password = body.get("password");
        todoService.deleteTodo(id, password);
        // 성공한 경우
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
