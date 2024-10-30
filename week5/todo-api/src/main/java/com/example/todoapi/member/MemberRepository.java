package com.example.todoapi.member;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MemberRepository {

    @PersistenceContext
    private EntityManager em;

    // 생성
    public void save(Member member) {
        em.persist(member);
    }

    // 조회
    // 단건 조회
    public Member findById(Long memberId) {
        return em.find(Member.class, memberId);
    }

    // 다건 조회
    public List<Member> findAll() {
        return em.createQuery("select t from Member as t", Member.class).getResultList();
    }

    // 조건 조회 (예 : 김_%)
    public List<Member> findAllByFirstName(String name) {
        return em.createQuery("select t from Member as t where t.name like concat(:input, '_', '%')", Member.class)
                .setParameter("input", name)
                .getResultList();
    }

    //수정
    //패스워드 업데이트만 가능하도록 구현하였음(Member.java 참고)

    //삭제
    public void deleteById(Long memberId) {
        Member member = findById(memberId);
        em.remove(member);
    }

    //테스트용
    public void flushAndClear() {
        em.flush();
        em.clear();
    }
}
