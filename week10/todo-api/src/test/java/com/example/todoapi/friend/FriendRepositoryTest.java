package com.example.todoapi.friend;

import com.example.todoapi.member.Member;
import com.example.todoapi.member.MemberRepository;
import jakarta.transaction.Transactional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class FriendRepositoryTest {

    @Autowired
    private FriendRepository friendRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Test
    @Transactional
    @Rollback(value = false)
    void friendSaveTest() {
        Member member1 = new Member("id1", "password1", "홍길동");
        Member member2 = new Member("id2", "password2", "홍길순");
        memberRepository.save(member1);
        memberRepository.save(member2);

        Friend friend = new Friend(member1, member2);
        friendRepository.save(friend);

        Assertions.assertThat(friend.getId()).isNotNull();
    }

    @Test
    @Transactional
    void friendFindOneByIdTest() {
        Member member1 = new Member("id1", "password1", "홍길동");
        Member member2 = new Member("id2", "password2", "홍길순");
        memberRepository.save(member1);
        memberRepository.save(member2);

        Friend friend = new Friend(member1, member2);
        friendRepository.save(friend);

        friendRepository.flushAndClear();

        Friend findFriend = friendRepository.findById(friend.getId());

        Assertions.assertThat(findFriend.getId()).isEqualTo(friend.getId());
    }

    @Test
    @Transactional
    void friendFindAllTest() {
        Member member1 = new Member("id1", "pass1", "홍길동");
        Member member2 = new Member("id2", "pass1", "홍길");
        Member member3 = new Member("id3", "pass1", "김아무개");
        memberRepository.save(member1);
        memberRepository.save(member2);
        memberRepository.save(member3);

        Friend friend1 = new Friend(member1, member2);
        Friend friend2 = new Friend(member1, member3);
        Friend friend3 = new Friend(member2, member3);
        friendRepository.save(friend1);
        friendRepository.save(friend2);
        friendRepository.save(friend3);

        List<Friend> friendList = friendRepository.findAll();

        //System.out.println();
        Assertions.assertThat(friendList).hasSize(3);
    }

    @Test
    @Transactional
    void friendFindAllByMemberTest() {
        Member member1 = new Member("id1", "pass1", "홍길동");
        Member member2 = new Member("id2", "pass2", "홍길");
        Member member3 = new Member("id3", "pass3", "김아무개");
        Member member4 = new Member("id4", "pass4", "박아무개");
        memberRepository.save(member1);
        memberRepository.save(member2);
        memberRepository.save(member3);
        memberRepository.save(member4);

        Friend friend1 = new Friend(member1, member2);
        Friend friend2 = new Friend(member1, member3);
        Friend friend3 = new Friend(member2, member3);
        Friend friend4 = new Friend(member1, member4);
        friendRepository.save(friend1);
        friendRepository.save(friend2);
        friendRepository.save(friend3);
        friendRepository.save(friend4);

        List<Friend> member1FriendList = friendRepository.findAllByMember(member1);
        List<Friend> member2FriendList = friendRepository.findAllByMember(member2);
        List<Friend> member3FriendList = friendRepository.findAllByMember(member3);
        List<Friend> member4FriendList = friendRepository.findAllByMember(member4);

        Assertions.assertThat(member1FriendList).hasSize(3);
        Assertions.assertThat(member2FriendList).hasSize(2);
        Assertions.assertThat(member3FriendList).hasSize(2);
        Assertions.assertThat(member4FriendList).hasSize(1);

    }

    //Update는 따로 안 만듦

    @Test
    @Transactional
    @Rollback(value = false)
    void friendDeleteTest() {
        Member member1 = new Member("id1", "pass1", "홍길동");
        Member member2 = new Member("id2", "pass2", "홍길");
        Member member3 = new Member("id3", "pass3", "김아무개");
        Member member4 = new Member("id4", "pass4", "박아무개");
        memberRepository.save(member1);
        memberRepository.save(member2);
        memberRepository.save(member3);
        memberRepository.save(member4);

        Friend friend1 = new Friend(member1, member2);
        Friend friend2 = new Friend(member1, member3);
        Friend friend3 = new Friend(member2, member3);
        Friend friend4 = new Friend(member1, member4);
        friendRepository.save(friend1);
        friendRepository.save(friend2);
        friendRepository.save(friend3);
        friendRepository.save(friend4);

        friendRepository.flushAndClear();

        friendRepository.deleteById(friend1.getId());
    }

    @AfterAll
    public static void doNotFinish() {
        System.out.println("test finished");
        while (true) {}
    }
}
