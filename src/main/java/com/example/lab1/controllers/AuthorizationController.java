package com.example.lab1.controllers;

import com.example.lab1.DTO.ResponseMessageDTO;
import com.example.lab1.DTO.UserDTO;
import com.example.lab1.exceptions.UserValidationException;
import com.example.lab1.service.UserRepositoryService;
import com.example.lab1.validation.ValidationUserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/users")
@Api(value = "Authorization api")
public class AuthorizationController {

    @Autowired
    private UserRepositoryService userRepositoryService;
    @Autowired
    private ValidationUserService validationUserService;

    @PutMapping("/register")
    public ResponseEntity<ResponseMessageDTO> register(@RequestBody UserDTO userDTO) {
        ResponseMessageDTO message = new ResponseMessageDTO();
        try{
            validationUserService.validateUserDTO(userDTO);
        }catch (UserValidationException e) {
            message.setAnswer(e.getErrMessage());
            return new ResponseEntity<>(message, e.getErrStatus());
        }
        return userRepositoryService.registerUserDTO(userDTO);
    }

    @PostMapping("/auth")
    public ResponseEntity<ResponseMessageDTO> auth(@RequestBody UserDTO userDTO){
        ResponseMessageDTO message = new ResponseMessageDTO();
        try{
            validationUserService.validateUserDTO_FOR_AUTH(userDTO);
        }catch (UserValidationException e) {
            message.setAnswer(e.getErrMessage());
            return new ResponseEntity<>(message, e.getErrStatus());
        }
        return userRepositoryService.authUserDTO(userDTO);
    }
}
