package com.example.todoapi.todo;

import com.example.todoapi.todo.dto.TodoCreateRequest;
import com.example.todoapi.todo.dto.TodoDeleteRequest;
import com.example.todoapi.todo.dto.TodoResponse;
import com.example.todoapi.todo.dto.TodoUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/todo")
public class TodoController {

    private final TodoService todoService;

    //@RequestMapping(method = RequestMethod.POST)
    @PostMapping
    public ResponseEntity<Void> createTodo(@RequestBody TodoCreateRequest request) throws Exception {
        Long todoId = todoService.createTodo(request.getContent(), request.getDeadline(), request.getImportance(), request.getMemberId());
        // todoService.createTodo(request); << 이렇게 짠 후 서비스 계층에서 처리하는 방법도 있음

        return ResponseEntity.created(URI.create("/todo/" + todoId)).build();
    }

    @GetMapping("/list/{memberId}")
    public ResponseEntity<List<Todo>> getAllTodoByMember(@PathVariable("memberId") Long memberId) throws Exception { // List<Todo> 대신 List<TodoResponse>와 같이 DTO를 사용할 수도 있음
        List<Todo> todoList = todoService.getAllTodoByMember(memberId);
        return ResponseEntity.ok().body(todoList);
    }

    @GetMapping("/list/{memberId}/{todoId}")
    public ResponseEntity<Todo> getOneTodo(@PathVariable("memberId") Long memberId, @PathVariable("todoId") Long todoId) throws Exception {
        Todo todo = todoService.getOneTodo(memberId, todoId);
        return ResponseEntity.ok().body(todo);
    }

    @GetMapping("/list")
    public ResponseEntity<List<Todo>> getAllTodo() throws Exception {
        List<Todo> todoList = todoService.getAllTodo();
        return ResponseEntity.ok().body(todoList);
    }

    @DeleteMapping("/{todoId}")
    public ResponseEntity<Void> deleteTodo(@PathVariable("todoId") Long todoId, @RequestBody TodoDeleteRequest request) throws Exception {
        todoService.deleteTodo(todoId, request.getMemberId());
        return ResponseEntity.noContent().build(); // 204 no content. 200 ok 써도 상관 없음
    }

    //PATCH
    @PatchMapping("/{todoId}")
    public ResponseEntity<Void> updateTodo(@PathVariable("todoId") Long todoId, @RequestBody TodoUpdateRequest request) throws Exception {
        todoService.updateTodo(todoId, request.getUpdateContent(), request.getMemberId());
        return ResponseEntity.ok().build();
    }

}
