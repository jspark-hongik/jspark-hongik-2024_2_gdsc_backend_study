package com.example.todoapi.member.dto;

import lombok.Getter;

@Getter
public class MemberCreateRequest {

    private String loginId;
    private String password;
    private String name;
}
