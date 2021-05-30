package com.example.lab1.service;

import com.example.lab1.DTO.AnswerDTO;
import com.example.lab1.DTO.QuestionDTO;
import com.example.lab1.DTO.TagsDTO;
import com.example.lab1.DTO.UserDTO;
import com.example.lab1.beans.Answer;
import com.example.lab1.beans.Question;
import com.example.lab1.beans.Tag;
import com.example.lab1.beans.User;
import com.example.lab1.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Service
public class DTOConverter {
/*
    Convert to DTO
 */
    @Autowired
    private TagRepository tagRepository;

    public QuestionDTO convertQuestionToDTO(Question question) {
        QuestionDTO questionDTO = new QuestionDTO();
        questionDTO.setId(question.getId());
        questionDTO.setScore(question.getScore());
        questionDTO.setStatus(question.getStatus());
//        questionDTO.setTags(question.getTags());
//        questionDTO.setTags(tagRepository.findByName(question.getUser()));
        questionDTO.setUser(question.getUser());
        questionDTO.setTitle(question.getTitle());
        questionDTO.setText(question.getText());
        return questionDTO;
    }

    public UserDTO convertUserToDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setLogin(user.getLogin());
        userDTO.setMail(user.getMail());
        userDTO.setPassword(user.getPassword());
        return userDTO;
    }

    public AnswerDTO convertAnswerToDTO(Answer answer) {
        AnswerDTO answerDTO = new AnswerDTO();
        answerDTO.setId(answer.getId());
        answerDTO.setScore(answer.getScore());
        answerDTO.setText(answer.getText());
        answerDTO.setUser(answer.getUser());
        answerDTO.setApprove(answerDTO.isApprove());
        return answerDTO;
    }

    public Set<TagsDTO> convertTagToTagsDTO(Set<Tag> tagSet) {
        Set<TagsDTO> resultDto = new HashSet<>();
        for (Tag tag:
             tagSet) {
            TagsDTO tagsDTO = new TagsDTO();
            tagsDTO.setId(tag.getId());
            tagsDTO.setName(tag.getName());
            resultDto.add(tagsDTO);
        }
        return resultDto;
    }


    /* Convert form DTO*/

    @Autowired
    private User user;
    @Autowired
    private Answer answer;
    @Autowired
    private Question question;
    @Autowired
    private Tag tag;

    public User convertUserFromDTO(UserDTO userDTO){
        user.setId(userDTO.getId());
        user.setLogin(userDTO.getLogin());
        user.setMail(userDTO.getMail());
        user.setPassword(userDTO.getPassword());
//        if(userDTO.getId() != null){
//            userDTO.setId(userDTO.getId());
//        }
        return user;
    }

    public Answer convertAnswerFromDTO(AnswerDTO answerDTO) {
        answer.setApprove(answerDTO.isApprove());
        answer.setId(answerDTO.getId());
        answer.setScore(answerDTO.getScore());
        answer.setText(answerDTO.getText());
        answer.setUser(answerDTO.getUser());
        return answer;
    }

    public Question convertQuestionFromDTO(QuestionDTO questionDTO) {
        question.setId(questionDTO.getId());
        question.setScore(questionDTO.getScore());
        question.setStatus(questionDTO.getStatus());
        question.setText(questionDTO.getText());
        question.setTitle(questionDTO.getTitle());
        question.setUser(questionDTO.getUser());
        return question;
    }

    public Set<Tag> convertTagFromDTO(Set<TagsDTO> tagsDTO, Long questionId) {
        Set<Tag> setTags = new HashSet<>();
        Set<Question> set = new HashSet<>();
        for (TagsDTO tags:
             tagsDTO) {
            tag.setId(tags.getId());
            tag.setName(tags.getName());
            tag.setQuestions(tags.getQuestions());
            setTags.add(tag);
        }
        return setTags;
    }
}
