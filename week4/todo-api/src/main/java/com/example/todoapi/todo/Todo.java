package com.example.todoapi.todo;

import com.example.todoapi.member.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "todo_id")
    private Long id;

    @Column(name = "todo_content", columnDefinition = "varchar(200)")
    private String content;

    @Column(name = "todo_is_checked", columnDefinition = "tinyint(1)")
    private boolean isChecked;

    @Column(name = "todo_start_time", columnDefinition = "date")
    private Date startTime;

    @Column(name = "todo_deadline", columnDefinition = "date")
    private Date deadline;

    @Column(name = "todo_importance", columnDefinition = "varchar(3)")
    private String importance;

    @Column(name = "todo_post_evaluation", columnDefinition = "tinyint")
    private short postEvaluation;

    @Column(name = "todo_review", columnDefinition = "varchar(200)")
    private String review;

    @JoinColumn(name = "member_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    public Todo(String content, boolean isChecked, Date startTime, Date deadline, String importance, short postEvaluation, String review, Member member) {
        this.content = content;
        this.isChecked = isChecked;
        this.startTime = startTime;
        this.deadline = deadline;
        this.importance = importance;
        this.postEvaluation = postEvaluation;
        this.review = review;
        this.member = member;
    }
}
