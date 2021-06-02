package com.example.lab1.controllers;

import com.example.lab1.DTO.AnswerDTO;
import com.example.lab1.DTO.ResponseMessageDTO;
import com.example.lab1.exceptions.QuestionNotFoundException;
import com.example.lab1.exceptions.UserNotFoundException;
import com.example.lab1.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/question/{qId}")
public class AnswerController {


    @Autowired
    private AnswerService answerService;


    @PutMapping("/answer")
    public ResponseEntity setAnswer(@PathVariable Long qId, AnswerDTO answerDTO, HttpServletRequest request){
        ResponseMessageDTO messageDTO = new ResponseMessageDTO();
        try {
            answerService.saveFromDTO(answerDTO, request, qId);
        } catch (UserNotFoundException e) {
            messageDTO.setAnswer(e.getErrMessage());
            return new ResponseEntity(messageDTO, e.getErrStatus());
        }catch (QuestionNotFoundException e){
            messageDTO.setAnswer(e.getErrMessage());
            return new ResponseEntity(messageDTO, e.getErrStatus());
        }
        messageDTO.setAnswer("Ответ добавлен");
        return new ResponseEntity(messageDTO, HttpStatus.ACCEPTED);
    }
}
