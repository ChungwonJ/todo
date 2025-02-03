package com.example.todo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TodoRequestDto {
    private String username;
    private String contents;
    private String password;
}

