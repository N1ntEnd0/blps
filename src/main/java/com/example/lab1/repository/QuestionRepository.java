package com.example.lab1.repository;

import com.example.lab1.beans.Question;
import com.example.lab1.beans.Tag;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.Set;


public interface QuestionRepository extends CrudRepository<Question, Long> {
    Optional<Question> findById(Long aLong);
}
