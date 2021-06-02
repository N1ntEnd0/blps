package com.example.lab1.repository;

import com.example.lab1.beans.Answer;
import com.example.lab1.beans.Question;
import org.springframework.data.repository.CrudRepository;

public interface AnswerRepository extends CrudRepository<Answer, Long> {

}
