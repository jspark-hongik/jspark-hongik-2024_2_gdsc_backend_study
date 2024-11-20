package com.example.todoapi.member.dto;

import com.example.todoapi.common.message.ErrorMessage;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class MemberLogoutRequest {

    @NotNull(message = ErrorMessage.MEMBER_ID_MUST_BE_NOT_NULL)
    private Long memberId;

    @NotNull(message = ErrorMessage.LOGIN_ID_MUST_BE_NOT_NULL)
    private String loginId;
}
