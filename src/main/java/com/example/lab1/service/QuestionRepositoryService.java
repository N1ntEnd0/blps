package com.example.lab1.service;

import com.example.lab1.DTO.QuestionDTO;
import com.example.lab1.beans.User;
import com.example.lab1.exceptions.UserNotFoundException;
import com.example.lab1.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
// #TODO validation!
public class QuestionRepositoryService {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private DTOConverter dtoConverter;


    @Autowired
    private TagRepositoryService repositoryService;



    @Autowired
    private UserRepositoryService userRepositoryService;


    public void saveFromDTO(QuestionDTO questionDTO, HttpServletRequest request) throws UserNotFoundException {
        try {
            User user = userRepositoryService.getUserFromRequest(request);
            this.questionRepository.save(dtoConverter.convertQuestionFromDTO(questionDTO, user, repositoryService.getAllTagsByQuery(questionDTO.getTags())));
        }catch (UserNotFoundException e){
            e.setErrMessage("User not found!");
            e.setErrStatus(HttpStatus.BAD_REQUEST);
            throw e;
        }
    }
}