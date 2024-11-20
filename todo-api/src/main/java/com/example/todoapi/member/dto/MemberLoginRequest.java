package com.example.todoapi.member.dto;

import com.example.todoapi.common.message.ErrorMessage;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class MemberLoginRequest {

    @NotNull(message = ErrorMessage.NOT_NULL)
    private String loginId;

    @NotNull(message = ErrorMessage.NOT_NULL)
    private String password;
}
