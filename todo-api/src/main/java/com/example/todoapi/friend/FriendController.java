package com.example.todoapi.friend;

import com.example.todoapi.friend.dto.FriendCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/friend")
public class FriendController {

    private final FriendService friendService;

    //친구 생성
    @PostMapping
    public ResponseEntity<Void> createFriend(@RequestBody FriendCreateRequest request) throws Exception {
        Long friendId = friendService.createFriend(request.getMemberId1(), request.getMemberId2());

        return ResponseEntity.created(URI.create("/friend" + friendId)).build();
    }

    @GetMapping("/list/{friendId}")
    public ResponseEntity<Friend> findOneFriend(@PathVariable("friendId") Long friendId) throws Exception {
        Friend friend = friendService.findOneFriend(friendId);
        return ResponseEntity.ok().body(friend);
    }

    @GetMapping("/list")
    public ResponseEntity<List<Friend>> getAllFriend() throws Exception {
        List<Friend> friendList = friendService.getAllFriend();
        return ResponseEntity.ok().body(friendList);
    }

    @GetMapping("/list/{memberId}")
    public ResponseEntity<List<Friend>> getAllFriendByMember(@PathVariable("memberId") Long memberId) throws Exception {
        List<Friend> friendList = friendService.getAllFriendByMember(memberId);
        return ResponseEntity.ok().body(friendList);
    }

    @DeleteMapping("/{friendId}")
    public ResponseEntity<Void> deleteFriend(@PathVariable("friendId") Long friendId, @RequestBody Long memberId) throws Exception {
        friendService.deleteFriend(friendId, memberId);
        return ResponseEntity.noContent().build();
    }
}
