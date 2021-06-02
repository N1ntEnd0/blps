package com.example.lab1.service;

import com.example.lab1.DTO.QuestionDTO;
import com.example.lab1.DTO.ResponseMessageDTO;
import com.example.lab1.beans.Question;
import com.example.lab1.beans.User;
import com.example.lab1.exceptions.QuestionNotFoundException;
import com.example.lab1.exceptions.TagNotFoundException;
import com.example.lab1.exceptions.UserNotFoundException;
import com.example.lab1.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Service
// #TODO validation!
public class QuestionRepositoryService {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private DTOConverter dtoConverter;


    @Autowired
    private TagRepositoryService tagRepositoryService;



    @Autowired
    private UserRepositoryService userRepositoryService;


    public void saveFromDTO(QuestionDTO questionDTO, HttpServletRequest request) throws UserNotFoundException {
        // #TODO validate done
        try {
            User user = userRepositoryService.getUserFromRequest(request);
            this.questionRepository.save(dtoConverter.convertQuestionFromDTO(questionDTO, user, tagRepositoryService.getAllTagsByQuery(questionDTO.getTags())));
        }catch (UserNotFoundException e){
            e.setErrMessage("User not found!");
            e.setErrStatus(HttpStatus.BAD_REQUEST);
            throw e;
        }
    }


    public ResponseEntity getAllQuestionsByTagName(String name) throws TagNotFoundException {
        ResponseMessageDTO messageDTO = new ResponseMessageDTO();
        ArrayList<Question> questions = (ArrayList<Question>) questionRepository.findAll();
        Set<QuestionDTO> questionDTOS = new HashSet<>();
        for (Question question: questions) {
                if (question.getTags().contains(tagRepositoryService.getTagByName(name))){
                    questionDTOS.add(dtoConverter.convertQuestionToDTO(question));
                }
            }
        return new ResponseEntity(questionDTOS, HttpStatus.OK);

    }


    public Question findById(Long id) throws QuestionNotFoundException {
        Question question = questionRepository.findById(id).get();
        if(question == null){
            throw new QuestionNotFoundException("Вопроса с таким id не существует!", HttpStatus.BAD_REQUEST);
        }
        return question;
    }
}
