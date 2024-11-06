package com.example.todoapi.member;

import jakarta.transaction.Transactional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Test
    @Transactional
    @Rollback(value = false)
    void memberSaveTest() {
        //given
        Member member = new Member("id1", "pass1", "홍길동");

        //when
        memberRepository.save(member);

        //then
        Assertions.assertThat(member.getId()).isNotNull();

    }

    @Test
    @Transactional
    void memberFindOneByIdTest() {
        //given
        Member member = new Member("id1", "pass1", "홍길동");
        memberRepository.save(member);

        memberRepository.flushAndClear();

        //when
        Member findMember = memberRepository.findById(member.getId());

        //then
        Assertions.assertThat(findMember.getId()).isEqualTo(member.getId());
    }

    @Test
    @Transactional
    void memberFindAllTest() {
        Member member1 = new Member("id1", "pass1", "홍길동");
        Member member2 = new Member("id2", "pass1", "홍길동");
        Member member3 = new Member("id3", "pass1", "홍길동");
        memberRepository.save(member1);
        memberRepository.save(member2);
        memberRepository.save(member3);

        List<Member> memberList = memberRepository.findAll();

        //System.out.println(memberList);
        Assertions.assertThat(memberList).hasSize(3);
    }

    @Test
    @Transactional
    void memberFindAllByFirstNameTest() {
        Member member1 = new Member("id1", "pass1", "홍길동");
        Member member2 = new Member("id2", "pass1", "홍길");
        Member member3 = new Member("id3", "pass1", "김아무개");
        memberRepository.save(member1);
        memberRepository.save(member2);
        memberRepository.save(member3);

        List<Member> memberListCondition1 = memberRepository.findAllByFirstName("홍");
        List<Member> memberListCondition2 = memberRepository.findAllByFirstName("김");

        Assertions.assertThat(memberListCondition1).hasSize(2);
        Assertions.assertThat(memberListCondition2).hasSize(1);
    }

    @Test
    @Transactional
    void memberFindAllByLastNameTest() {
        Member member1 = new Member("id1", "pass1", "홍길동");
        Member member2 = new Member("id2", "pass1", "박길동");
        Member member3 = new Member("id3", "pass1", "선우동수");
        Member member4 = new Member("id4", "pass1", "남궁규");
        Member member5 = new Member("id5", "pass1", "박규현");
        Member member6 = new Member("id6", "pass1", "박상규");
        memberRepository.save(member1);
        memberRepository.save(member2);
        memberRepository.save(member3);
        memberRepository.save(member4);
        memberRepository.save(member5);
        memberRepository.save(member6);

        List<Member> memberListCondition1 = memberRepository.findAllByLastName("길동");
        List<Member> memberListCondition2 = memberRepository.findAllByLastName("동수");
        List<Member> memberListCondition3 = memberRepository.findAllByLastName("규");

        Assertions.assertThat(memberListCondition1).hasSize(2);
        Assertions.assertThat(memberListCondition2).hasSize(1);
        Assertions.assertThat(memberListCondition3).hasSize(2);
    }

    @Test
    @Transactional
    @Rollback(value = false)
    void memberUpdateTest() {
        Member member1 = new Member("id1", "pass1", "홍길동");
        memberRepository.save(member1);

        memberRepository.flushAndClear();

        Member findMember1 = memberRepository.findById(member1.getId());
        findMember1.updatePassword("newpass1");
    }

    @Test
    @Transactional
    @Rollback(value = false)
    void memberDeleteTest() {
        Member member1 = new Member("id1", "pass1", "홍길동");
        Member member2 = new Member("id2", "pass1", "홍길");
        Member member3 = new Member("id3", "pass1", "김아무개");
        memberRepository.save(member1);
        memberRepository.save(member2);
        memberRepository.save(member3);

        memberRepository.flushAndClear();

        memberRepository.deleteById(member1.getId());

    }

    @AfterAll
    public static void doNotFinish() {
        System.out.println("test finished");
        while (true) {}
    }
}
