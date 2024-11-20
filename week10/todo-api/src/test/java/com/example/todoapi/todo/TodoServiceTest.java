package com.example.todoapi.todo;

import com.example.todoapi.member.Member;
import com.example.todoapi.member.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TodoServiceTest {
    @Mock
    private TodoRepository todoRepository;

    @Mock
    private MemberRepository memberRepository;

    @InjectMocks
    private TodoService todoService;

    @Test
    void createTodoTest() throws Exception {
        //given (테스트를 실행할 때 필요한 기본 환경 세팅)
        BDDMockito.given(memberRepository.findById(1L)).willReturn(new Member("", "", ""));

        //when (테스트하려는 그 동작을 실행)
        todoService.createTodo("content","20241231", 5, 1L);

        //then (그 동작이 실행되었을 때, 우리가 기대하는 결과가 올바르게 나오는지 확인)
        verify(todoRepository, times(1)).save(any(Todo.class));
    }

    @Test
    public void createTodoTest_When_MemberIdDoesNotExist() throws Exception {
        //given
        BDDMockito.given(memberRepository.findById(anyLong())).willReturn(null);

        //when & then
        //멤버가 존재하지 않는다는 에러가 나오기를 기대
        Assertions.assertThatThrownBy(() -> {
            todoService.createTodo("content", "20241231", 5, 999999L);
        }).hasMessageContaining("존재하지 않는 멤버입니다.")
                .isInstanceOf(Exception.class);

    }

    @Test
    public void getOneTodoTest() throws Exception {
        //given
        BDDMockito.given(todoRepository.findById(1L)).willReturn(new Todo("", "", 0, new Member("", "", "")));

        //when
        todoService.getOneTodo(1L, 1L);

        //then
        verify(todoRepository, times(1)).findById(any(Long.class));
    }

    @Test
    public void getOneTodoTest_When_TodoDoesNotExist() throws Exception {
        //given
        BDDMockito.given(todoRepository.findById(anyLong())).willReturn(null);

        //when & then
        //할 일이 존재하지 않는다는 에러가 나오기를 기대
        Assertions.assertThatThrownBy(() -> {
            todoService.getOneTodo(999999L, 999999L);
        }).hasMessageContaining("존재하지 않는 할 일 입니다.")
                .isInstanceOf(Exception.class);
    }

    @Test
    public void getAllTodoTest() {
        //given
        //BDDMockito.given(todoRepository.findById(1L)).willReturn(new Todo("", "", 0, new Member("", "", "")));
        //BDDMockito.given(todoRepository.findById(2L)).willReturn(new Todo("", "", 0, new Member("", "", "")));

        //when
        todoService.getAllTodo();

        //then

        verify(todoRepository, atLeastOnce()).findAll();
    }



}
