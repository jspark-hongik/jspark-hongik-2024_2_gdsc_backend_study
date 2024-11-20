package com.example.todoapi.member.dto;

import com.example.todoapi.common.message.ErrorMessage;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

@Getter
public class MemberCreateRequest {

    @Length(min = 4, message = ErrorMessage.LOGIN_ID_MIN)
    @NotNull(message = ErrorMessage.NOT_NULL)
    private String loginId;

    @Length(min = 8, max = 20, message = ErrorMessage.PASSWORD_RANGE)
    @NotNull(message = ErrorMessage.NOT_NULL)
    private String password;

    @Length(min = 2, message = ErrorMessage.NAME_MIN)
    @NotNull(message = ErrorMessage.NOT_NULL)
    private String name;
}
