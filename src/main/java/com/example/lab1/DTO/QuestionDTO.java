package com.example.lab1.DTO;

import com.example.lab1.beans.Tag;
import com.example.lab1.beans.User;
import java.util.Set;

public class QuestionDTO {

    private Long id;
    private String Title;
    private String text;
    private String status;
    private Set<TagsDTO> tags;
    private User user;
    private int score;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
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

    public Set<TagsDTO> getTags() {
        return tags;
    }

    public void setTags(Set<TagsDTO> tags) {
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
