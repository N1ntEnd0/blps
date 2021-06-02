package com.example.lab1.beans;


import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
@Table(name = "stackoverflow_answer")
public class Answer {
    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    private Question question;

    @NotNull
    private boolean approve;
    @NotNull
    private String text;
    @NotNull
    private int score;
    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;


    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isApprove() {
        return approve;
    }

    public void setApprove(boolean approve) {
        this.approve = approve;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
