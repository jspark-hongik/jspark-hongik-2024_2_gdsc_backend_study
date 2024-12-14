package com.example.todoapi.friend.dto;

import com.example.todoapi.common.message.ErrorMessage;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class FriendCreateRequest {

    @NotNull(message = ErrorMessage.MEMBER_ID_MUST_BE_NOT_NULL)
    private Long memberId1;

    @NotNull(message = ErrorMessage.MEMBER_ID_MUST_BE_NOT_NULL)
    private Long memberId2;
}
