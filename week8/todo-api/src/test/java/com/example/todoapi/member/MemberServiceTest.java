package com.example.todoapi.member;

import com.example.todoapi.todo.Todo;
import com.example.todoapi.todo.TodoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class MemberServiceTest {

    @Mock
    private TodoRepository todoRepository;

    @Mock
    private MemberRepository memberRepository;


    @InjectMocks
    private MemberService memberService;

    @Test
    void createMemberTest() throws Exception {
        //given (테스트를 실행할 때 필요한 기본 환경 세팅)

        //when (테스트하려는 그 동작을 실행)
        memberService.createMember("loginIdTest", "passwordTest", "nameTest");

        //then (그 동작이 실행되었을 때, 우리가 기대하는 결과가 올바르게 나오는지 확인)
        verify(memberRepository, times(1)).save(any(Member.class));
    }
}
