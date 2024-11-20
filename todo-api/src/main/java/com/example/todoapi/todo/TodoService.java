package com.example.todoapi.todo;

import com.example.todoapi.common.exception.BadRequestException;
import com.example.todoapi.common.message.ErrorMessage;
import com.example.todoapi.member.Member;
import com.example.todoapi.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;
    private final MemberRepository memberRepository;

    //할 일 생성
    @Transactional
    public Long createTodo(String content, String deadline, int importance, Long memberId) throws Exception {
        Member member = memberRepository.findById(memberId);

        if(member == null) {
            throw new BadRequestException(ErrorMessage.MEMBER_NOT_EXISTS);
        }

        Todo todo = new Todo(content, deadline, importance, member);
        todoRepository.save(todo);
        return todo.getId();
    }

    //할 일 조회(하나)
    @Transactional(readOnly = true)
    public Todo getOneTodo(Long memberId, Long todoId) throws Exception {
        Member member = memberRepository.findById(memberId);
        Todo todo = todoRepository.findById(todoId);

        if(member == null) {
            throw new BadRequestException(ErrorMessage.MEMBER_NOT_EXISTS);
        }

        if(todo == null) {
            throw new BadRequestException(ErrorMessage.TODO_NOT_EXISTS);
        }

        if (todo.getMember() != member) {
            throw new BadRequestException(ErrorMessage.UNAUTHORIZED);
        }

        return todo;
    }

    //할 일 조회(모든 멤버의 모든 할 일)
    @Transactional(readOnly = true)
    public List<Todo> getAllTodo() {
        return todoRepository.findAll();
    }

    //할 일 조회(특정 멤버의 모든 할 일 조회)
    @Transactional(readOnly = true)
    public List<Todo> getAllTodoByMember(Long memberId) throws Exception {
        Member member = memberRepository.findById(memberId);

        if(member == null) {
            throw new BadRequestException(ErrorMessage.MEMBER_NOT_EXISTS);
        }

        return todoRepository.findAllByMember(member);
    }

    //할 일 수정
    @Transactional
    public void updateTodo(Long todoId, String newContent, Long memberId) throws Exception {
        Todo todo = todoRepository.findById(todoId);
        Member member = memberRepository.findById(memberId);

        if (todo == null) {
            throw new BadRequestException(ErrorMessage.TODO_NOT_EXISTS);
        }

        if (member == null) {
            throw new BadRequestException(ErrorMessage.MEMBER_NOT_EXISTS);
        }

        if (todo.getMember() != member) {
            throw new BadRequestException(ErrorMessage.UNAUTHORIZED);
        }

        todo.updateContent(newContent);
    }

    //할 일 삭제
    @Transactional
    public void deleteTodo(Long todoId, Long memberId) throws Exception {
        Todo todo = todoRepository.findById(todoId);
        Member member = memberRepository.findById(memberId);

        if (todo == null) {
            throw new BadRequestException(ErrorMessage.TODO_NOT_EXISTS);
        }

        if (member == null) {
            throw new BadRequestException(ErrorMessage.MEMBER_NOT_EXISTS);
        }

        if (todo.getMember() != member) {
            throw new BadRequestException(ErrorMessage.UNAUTHORIZED);
        }

        todoRepository.deleteById(todoId);
    }
}
