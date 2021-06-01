package com.example.lab1.repository;

import com.example.lab1.beans.Question;
import com.example.lab1.beans.QuestionTags;
import org.springframework.data.repository.CrudRepository;

public interface QuestionTagsRepository extends CrudRepository<QuestionTags, Long> {

}
