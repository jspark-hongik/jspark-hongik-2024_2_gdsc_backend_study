package com.example.todoapi.todo.dto;

import lombok.Getter;

@Getter
public class TodoCreateRequest {
    private String content;
    private String deadline;
    private int importance;
    private Long memberId;

}
