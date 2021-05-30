package com.example.lab1.beans;


import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.Set;


@Entity
@Table(name = "stackoverflow_tags",uniqueConstraints =
@UniqueConstraint(columnNames = "name"))
public class Tag {
    @Id
    @GeneratedValue
    private Long id;
    @NotNull
    private String name;
    @ManyToMany(mappedBy = "tags")
    private Set<Question> questions;

    public Set<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(Set<Question> questions) {
        this.questions = questions;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
