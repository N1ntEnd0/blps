package com.example.lab1.beans;


import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "stackoverflow_question")
public class Question {
    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private String title;
    @NotNull
    private String text;
    @NotNull
    private String status;
    @NotNull
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Tag> tags;
    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;
    @NotNull
    private int score;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
