package com.example.todoapi.member;

import com.example.todoapi.member.dto.MemberCreateRequest;
import com.example.todoapi.member.dto.MemberLoginRequest;
import com.example.todoapi.member.dto.MemberLogoutRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    //멤버 생성(회원가입, register)
    @PostMapping
    public ResponseEntity<Void> createMember(@RequestBody @Valid MemberCreateRequest request) throws Exception {
        Long memberId = memberService.createMember(request.getLoginId(), request.getPassword(), request.getName());

        return ResponseEntity.created(URI.create("/register/" + memberId)).build();
    }

    @GetMapping("/list/{memberId}")
    public ResponseEntity<Member> findOneMember(@PathVariable("memberId") Long memberId) throws Exception {
        Member member = memberService.findOneMember(memberId);
        return ResponseEntity.ok().body(member);
    }

    @GetMapping("/list")
    public ResponseEntity<List<Member>> findAllMember() throws Exception {
        List<Member> memberList = memberService.findAllMember();
        return ResponseEntity.ok().body(memberList);
    }

    @GetMapping("/list/{firstName}")
    public ResponseEntity<List<Member>> findAllMemberByFirstName(@PathVariable("firstName") String firstName) throws Exception {
        List<Member> memberList = memberService.findAllMemberByFirstName(firstName);
        return ResponseEntity.ok().body(memberList);
    }

    @GetMapping("/list/{lastName}")
    public ResponseEntity<List<Member>> findAllMemberByLastName(@PathVariable("lastName") String lastName) throws Exception {
        List<Member> memberList = memberService.findAllMemberByLastName(lastName);
        return ResponseEntity.ok().body(memberList);
    }

    @PatchMapping("/{memberId}/patch_password")
    public ResponseEntity<Void> updatePassword(@PathVariable("memberId") Long memberId, @RequestBody @Valid String newPassword) throws Exception {
        memberService.updatePassword(memberId, newPassword);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{memberId}/patch_name")
    public ResponseEntity<Void> updateName(@PathVariable("memberId") Long memberId, @RequestBody @Valid String newName) throws Exception {
        memberService.updatePassword(memberId, newName);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{memberId}")
    public ResponseEntity<Void> deleteMember(@PathVariable("memberId") Long memberId) throws Exception {
        memberService.deleteMember(memberId);
        return ResponseEntity.noContent().build();
    }

    //로그인?
    //종료 코드 1(으)로 완료된 프로세스 << TodoApiApplication 실행 시 해당 오류 발생해서 수정
    //@PostMapping 에서 @PostMapping("/login")으로 수정
    @PostMapping("/login")
    public ResponseEntity<Long> login(@RequestBody @Valid MemberLoginRequest request) throws Exception {
        Long memberId = memberService.login(request.getLoginId(), request.getPassword());

        return ResponseEntity.ok().body(memberId);
    }

    //로그아웃?
    //얘도 @PostMapping("/logout")으로 수정
    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@RequestBody @Valid MemberLogoutRequest request) throws Exception {
        memberService.logout(request.getMemberId(), request.getLoginId());
        return ResponseEntity.ok().build();
    }
}
