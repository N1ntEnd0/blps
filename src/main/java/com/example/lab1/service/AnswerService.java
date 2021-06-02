package com.example.lab1.service;

import com.example.lab1.DTO.AnswerDTO;
import com.example.lab1.beans.Answer;
import com.example.lab1.beans.User;
import com.example.lab1.exceptions.QuestionNotFoundException;
import com.example.lab1.exceptions.UserNotFoundException;
import com.example.lab1.repository.AnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class AnswerService {


    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private UserRepositoryService userRepositoryService;

    @Autowired
    private QuestionRepositoryService questionRepositoryService;

    @Autowired
    private DTOConverter converter;



    public Answer getAnswersForQuestion(Long questionId){
        return null;
    }

    public void saveFromDTO(AnswerDTO answerDTO, HttpServletRequest request, Long qId) throws UserNotFoundException, QuestionNotFoundException {
        System.out.println(answerDTO.getText());
        User user = userRepositoryService.getUserFromRequest(request);
        answerRepository.save(converter.convertAnswerFromDTO(answerDTO, user, questionRepositoryService.findById(qId)));
    }






}
