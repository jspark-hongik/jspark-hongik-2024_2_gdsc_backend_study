package com.example.todoapi.todo.dto;

import com.example.todoapi.common.message.ErrorMessage;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class TodoDeleteRequest {
    @NotNull(message = ErrorMessage.MEMBER_ID_MUST_BE_NOT_NULL)
    private Long memberId;
}
