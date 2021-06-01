package com.example.lab1.beans;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "stackoverflow_question_tags")
public class QuestionTags {

    @Id
    private Long questionsId;
    @Id
    private Long tagsId;

    public Long getQuestionsId() {
        return questionsId;
    }

    public void setQuestionsId(Long questionsId) {
        this.questionsId = questionsId;
    }

    public Long getTagsId() {
        return tagsId;
    }

    public void setTagsId(Long tags_id) {
        this.tagsId = tags_id;
    }
}
