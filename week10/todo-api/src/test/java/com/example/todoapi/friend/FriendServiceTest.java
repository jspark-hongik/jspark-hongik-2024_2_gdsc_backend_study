package com.example.todoapi.friend;

import com.example.todoapi.member.Member;
import com.example.todoapi.member.MemberRepository;
import com.example.todoapi.todo.TodoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FriendServiceTest {

    @Mock
    private TodoRepository todoRepository;

    @Mock
    private MemberRepository memberRepository;

    @Mock
    private FriendRepository friendRepository;

    @InjectMocks
    private FriendService friendService;

    @Test
    void createFriendTest() throws Exception {
        //given
        BDDMockito.given(memberRepository.findById(1L)).willReturn(new Member("", "", ""));
        BDDMockito.given(memberRepository.findById(2L)).willReturn(new Member("", "", ""));

        //when
        friendService.createFriend(1L, 2L);

        //then
        verify(friendRepository, times(1)).save(any(Friend.class));
    }

}
