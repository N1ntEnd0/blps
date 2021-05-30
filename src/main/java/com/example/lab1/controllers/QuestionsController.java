package com.example.lab1.controllers;

import com.example.lab1.DTO.QuestionDTO;
import com.example.lab1.DTO.ResponseMessageDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/question")
public class QuestionsController {

    @PostMapping("/add")
    public ResponseMessageDTO addQuestion(@RequestBody QuestionDTO questionDTO) {
        ResponseMessageDTO responseMessageDTO = new ResponseMessageDTO();
        responseMessageDTO.setAnswer("okey");
        return responseMessageDTO;
    }
}
