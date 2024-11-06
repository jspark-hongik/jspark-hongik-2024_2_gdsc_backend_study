package com.example.todoapi.todo;

import com.example.todoapi.member.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.sql.Date;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter

public class Todo {

    public class CurrentDateTime { // startTime을 포함한 모든 변수를 CurrentDateTime 클래스로 감싸면 에러 뜨던데 왜일까. 애초에 이거도 잘못 쓰고 있는 것 같다.
        LocalDateTime now = LocalDateTime.now();
        String formatedNow = now.format(DateTimeFormatter.ofPattern("yyyy.MM.dd. HH:mm"));

        @Column(name = "todo_start_time", columnDefinition = "date")
        private String startTime = formatedNow;
    }
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "todo_id")
        private Long id;

        @Column(name = "todo_content", columnDefinition = "varchar(200)")
        private String content;

        @Column(name = "todo_is_checked", columnDefinition = "tinyint(1)")
        private boolean isChecked = false;


        @Column(name = "todo_deadline", columnDefinition = "date")
        private String deadline;

        @Column(name = "todo_importance", columnDefinition = "tinyint")
        private int importance;

        @Column(name = "todo_post_evaluation", columnDefinition = "tinyint")
        private int postEvaluation = -1;

        @Column(name = "todo_review", columnDefinition = "varchar(200)")
        private String review = "";

        @JoinColumn(name = "member_id")
        @ManyToOne(fetch = FetchType.LAZY)
        private Member member;

        public Todo(String content, String deadline, int importance, Member member) {
            this.content = content;
            this.deadline = deadline;
            this.importance = importance;
            this.member = member;
        }

        public void updateContent(String newContent) {
            this.content = newContent;
        }


}
