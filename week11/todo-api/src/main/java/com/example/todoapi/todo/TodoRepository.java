package com.example.todoapi.todo;


import com.example.todoapi.member.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TodoRepository {

    @PersistenceContext
    private EntityManager em;

    // 생성
    public void save(Todo todo) {
        em.persist(todo);
    }

    // 조회
    // 단건 조회 (한 개의 데이터 조회)
    public Todo findById(Long todoId) {
        return em.find(Todo.class, todoId);
    }

    // 다건 조회 (여러 개의 데이터 조회)
    public List<Todo> findAll() {
        return em.createQuery("select t from Todo as t", Todo.class).getResultList();
    }

    // 조건 조회 (여러 개의 데이터 중에 조건에 맞는 데이터만 조회)
    public List<Todo> findAllByMember(Member member) {
        return em.createQuery("select t from Todo as t where t.member = :todo_member", Todo.class)
                .setParameter("todo_member", member)
                .getResultList();
    }

    // 수정
    // 엔티티 클래스의 필드를 수정하는 메서드를 작성해서 수정하자

    // 삭제
    public void deleteById(Long todoId) {
        Todo todo = findById(todoId);
        em.remove(todo);
    }

    // TEST 용도로만 사용
    public void flushAndClear() {
        em.flush();
        em.clear();
    }
}
