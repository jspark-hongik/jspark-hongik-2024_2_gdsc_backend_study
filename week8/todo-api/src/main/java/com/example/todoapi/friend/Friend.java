package com.example.todoapi.friend;

import com.example.todoapi.member.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Friend {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "friend_id")
    private Long id;

    @JoinColumn(name = "member_id1")
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member1;

    @JoinColumn(name = "member_id2")
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member2;

    public Friend(Member member1, Member member2) {
        this.member1 = member1;
        this.member2 = member2;
    }
}