package com.example.todoapi.friend;

import com.example.todoapi.member.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FriendRepository {

    @PersistenceContext
    private EntityManager em;

    //생성
    public void save(Friend friend) {
        em.persist(friend);
    }

    //조회
    //단건 조회
    public Friend findById(Long friendId) {
        return em.find(Friend.class, friendId);
    }

    //다건 조회
    public List<Friend> findAll() {
        return em.createQuery("select t from Friend as t", Friend.class).getResultList();
    }

    //조건 조회
    public List<Friend> findAllByMember(Member member) {
        return em.createQuery("select t from Friend as t where (t.member1 = :friend_member) or (t.member2 = :friend_member)", Friend.class)
                .setParameter("friend_member", member)
                .getResultList();
    }

    //수정
    //구현 안함

    //삭제
    public void deleteById(Long friendId) {
        Friend friend = findById(friendId);
        em.remove(friend);
    }

    // TEST 용도로만 사용
    public void flushAndClear() {
        em.flush();
        em.clear();
    }
}
