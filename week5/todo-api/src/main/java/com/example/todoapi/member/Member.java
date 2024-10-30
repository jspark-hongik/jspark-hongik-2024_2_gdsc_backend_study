package com.example.todoapi.member;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(name = "member_login", columnDefinition = "varchar(20)")
    private String login;

    @Column(name = "member_password", columnDefinition = "varchar(20)")
    private String password;

    @Column(name = "member_name", columnDefinition = "varchar(20)")
    private String name;

    public Member(String login, String password, String name) {
        this.login = login;
        this.password = password;
        this.name = name;
    }

    //LoginId와 Name 변경은 정책상 허용하지 않는다고 하자
    public void updatePassword(String newPassword) {
        this.password = newPassword;
    }
}
