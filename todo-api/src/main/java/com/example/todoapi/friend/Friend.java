package com.example.todoapi.friend;

import com.example.todoapi.member.Member;
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
public class Friend {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "friend_id")
    @JsonProperty
    private Long id;

    @JoinColumn(name = "member_id1")
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonProperty
    private Member member1;

    @JoinColumn(name = "member_id2")
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonProperty
    private Member member2;

    public Friend(Member member1, Member member2) {
        this.member1 = member1;
        this.member2 = member2;
    }
}