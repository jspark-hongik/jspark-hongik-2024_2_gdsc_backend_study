package com.example.todoapi.member;

import com.example.todoapi.common.exception.BadRequestException;
import com.example.todoapi.common.message.ErrorMessage;
import com.example.todoapi.todo.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final TodoRepository todoRepository;
    private final MemberRepository memberRepository;

    //멤버 생성(회원가입)
    @Transactional
    public Long createMember(String loginId, String password, String name) throws Exception {
        if (loginId.length() < 4) {
            throw new BadRequestException(ErrorMessage.LOGIN_ID_MIN);
        }

        if (password.length() < 8 || password.length() > 20) {
            throw new BadRequestException(ErrorMessage.PASSWORD_RANGE);
        }

        if (name.length() < 2) {
            throw new BadRequestException(ErrorMessage.NAME_MIN);
        }

        Member member = new Member(loginId, password, name);
        memberRepository.save(member);
        return member.getId();
    }

    //멤버 조회(하나)
    @Transactional(readOnly = true)
    public Member findOneMember(Long memberId) throws Exception {
        Member member = memberRepository.findById(memberId);

        if (member == null) {
            throw new BadRequestException(ErrorMessage.MEMBER_NOT_EXISTS);
        }

        return member;
    }

    //멤버 조회(모두)
    @Transactional(readOnly = true)
    public List<Member> findAllMember() {
        return memberRepository.findAll();
    }

    //멤버 조회(조건1)
    @Transactional(readOnly = true)
    public List<Member> findAllMemberByFirstName(String firstName) {
        return memberRepository.findAllByFirstName(firstName);
    }

    //멤버 조회(조건2)
    @Transactional(readOnly = true)
    public List<Member> findAllMemberByLastName(String lastName) {
        return memberRepository.findAllByLastName(lastName);
    }

    //멤버 패스워드 수정
    @Transactional
    public void updatePassword(Long memberId, String newPassword) throws Exception {
        Member member = memberRepository.findById(memberId);

        if (member == null) {
            throw new BadRequestException(ErrorMessage.MEMBER_NOT_EXISTS);
        }

        member.updatePassword(newPassword);
    }

    //멤버 이름 수정
    @Transactional
    public void updateName(Long memberId, String newName) throws Exception {
        Member member = memberRepository.findById(memberId);

        if (member == null) {
            throw new BadRequestException(ErrorMessage.MEMBER_NOT_EXISTS);
        }

        if (newName.length() < 2) {
            throw new BadRequestException(ErrorMessage.NAME_MIN);
        }

        member.updateName(newName);
    }

    //멤버 삭제(회원탈퇴, 약관 위반 이용자 제재 등)
    @Transactional
    public void deleteMember(Long memberId) throws Exception {
        Member member = memberRepository.findById(memberId);

        if (member == null) {
            throw new BadRequestException(ErrorMessage.MEMBER_NOT_EXISTS);
        }

        //멤버 삭제 전에 해당 멤버의 할 일들도 먼저 다 삭제시켜야 하나? 일단 구현은 패스
        memberRepository.deleteById(memberId);
    }

    //로그인(?)
    @Transactional
    public Long login(String loginId, String password) throws Exception {
        Member member = memberRepository.findByLoginId(loginId);

        if (member == null) {
            throw new BadRequestException(ErrorMessage.LOGIN_ID_NOT_EXISTS);
        }

        if (member.getPassword() != password) {
            throw new BadRequestException(ErrorMessage.WRONG_PASSWORD);
        }

        return member.getId();
    }
    //로그아웃
    @Transactional
    public void logout(Long memberId, String loginId) throws Exception {
        Member member = memberRepository.findByLoginId(loginId);

        if (member == null) {
            throw new BadRequestException(ErrorMessage.MEMBER_NOT_EXISTS);
        }

        if (member.getLoginId() != loginId) {
            throw new BadRequestException(ErrorMessage.UNAUTHORIZED);
        }
    }
}
