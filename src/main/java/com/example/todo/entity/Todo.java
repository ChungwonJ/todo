package com.example.todo.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Todo {
    private Long id;
    private String username;
    private String contents;
    private String password;
    private LocalDateTime date;

    public Todo(String username, String contents, String password) {
        this.username = username;
        this.contents = contents;
        this.password = password;
        this.date = LocalDateTime.now();
    }
}
