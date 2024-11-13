package com.example.todoapi.friend.dto;

import lombok.Getter;

@Getter
public class FriendCreateRequest {
    private Long memberId1;
    private Long memberId2;
}
