package com.example.todo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class TodoResponseDto {
    private Long id;
    private String username;
    private String contents;
    private LocalDateTime date;

    public TodoResponseDto(TodoResponseDto todo) {
        this.id = todo.getId();
        this.username = todo.getUsername();
        this.contents = todo.getContents();
        this.date = todo.getDate();
    }
}
