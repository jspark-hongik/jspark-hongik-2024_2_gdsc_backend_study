package com.example.todoapi.member.dto;

import lombok.Getter;

@Getter
public class MemberLogoutRequest {
    private Long memberId;
    private String loginId;
}
