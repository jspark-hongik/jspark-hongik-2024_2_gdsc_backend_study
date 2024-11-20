package com.example.todoapi.member;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    @JsonProperty
    private Long id;

    @Column(name = "member_loginId", columnDefinition = "varchar(20)")
    @JsonProperty
    private String loginId;

    @Column(name = "member_password", columnDefinition = "varchar(20)")
    @JsonProperty
    private String password;

    @Column(name = "member_name", columnDefinition = "varchar(20)")
    @JsonProperty
    private String name;

    public Member(String loginId, String password, String name) {
        this.loginId = loginId;
        this.password = password;
        this.name = name;
    }

    //LoginId 변경은 정책상 허용하지 않는다고 하자
    public void updatePassword(String newPassword) {
        this.password = newPassword;
    }

    public void updateName(String newName) {
        this.name = newName;
    }
}
