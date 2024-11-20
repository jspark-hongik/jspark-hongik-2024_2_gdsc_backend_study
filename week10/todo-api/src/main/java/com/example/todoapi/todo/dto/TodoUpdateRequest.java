package com.example.todoapi.todo.dto;

import com.example.todoapi.common.message.ErrorMessage;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

@Getter
public class TodoUpdateRequest {
    @NotNull(message = ErrorMessage.MEMBER_ID_MUST_BE_NOT_NULL)
    private Long memberId;
    @Length(max = 50, message = ErrorMessage.TODO_CONTENT_MAX)
    private String updateContent;

}