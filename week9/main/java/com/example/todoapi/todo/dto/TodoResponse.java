package com.example.todoapi.todo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TodoResponse {
    private Long memberId;
    private String content;
}
