package com.example.lab1.controllers;

import com.example.lab1.DTO.QuestionDTO;
import com.example.lab1.DTO.ResponseMessageDTO;
import com.example.lab1.exceptions.TagNotFoundException;
import com.example.lab1.exceptions.UserNotFoundException;
import com.example.lab1.service.QuestionRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/question")
public class QuestionsController {

    @Autowired
    private QuestionRepositoryService questionRepositoryService;

    @PutMapping("/add")
    public ResponseEntity<ResponseMessageDTO> addQuestion(@RequestBody QuestionDTO questionDTO, HttpServletRequest request) {
        // #TODO validate
        ResponseMessageDTO responseMessageDTO = new ResponseMessageDTO();
        try {
            questionRepositoryService.saveFromDTO(questionDTO, request);
        }catch (UserNotFoundException e){
            responseMessageDTO.setAnswer(e.getErrMessage());
            return new ResponseEntity<>(responseMessageDTO, e.getErrStatus());
        }
        responseMessageDTO.setAnswer("okey");
        return new ResponseEntity<>(responseMessageDTO, HttpStatus.OK);
    }


    @GetMapping("/all/by-tage/{tagName}")
    public ResponseEntity getAllQuestion(@PathVariable String tagName){
        // #TODO validate
        try {
            return new ResponseEntity(questionRepositoryService.getAllQuestionsByTagName(tagName), HttpStatus.OK);

        } catch (TagNotFoundException e){
            ResponseMessageDTO messageDTO = new ResponseMessageDTO();
            messageDTO.setAnswer(e.getErrMessage());
            return new ResponseEntity<>(messageDTO, e.getErrStatus());
    }

    }
}
