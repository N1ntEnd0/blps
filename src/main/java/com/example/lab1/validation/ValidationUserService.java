package com.example.lab1.validation;

import com.example.lab1.DTO.UserDTO;
import com.example.lab1.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class ValidationUserService {

    public void validateUserDTO(UserDTO userDTO) throws UserValidationException {
        if(!validatePasswordUserDTO(userDTO.getPassword())){
            throw new UserValidationException("Invalid password. Please, try again", HttpStatus.UNAUTHORIZED);
        }else if(!validateEmailUserDTO(userDTO.getMail())){
            throw new UserValidationException("Invalid email. Please, try again", HttpStatus.BAD_REQUEST);
//        }else if(!validateSurnameUserDTO(userDTO.getSurname())){
//            throw new UserValidationException("Invalid surname. Please, try again", HttpStatus.BAD_REQUEST);
        }else if(!validateLoginUserDTO(userDTO.getLogin())){
            throw new UserValidationException("Invalid name. Please, try again", HttpStatus.BAD_REQUEST);
        }

    }

    public void validateUserDTO_FOR_AUTH(UserDTO userDTO) throws UserValidationException{
        if(!validatePasswordUserDTO(userDTO.getPassword())){
            throw new UserValidationException("Invalid password. Please, try again", HttpStatus.UNAUTHORIZED);
        }else if(!validateLoginUserDTO(userDTO.getLogin())){
            throw new UserValidationException("Invalid name. Please, try again", HttpStatus.BAD_REQUEST);
        }
    }


    private boolean validatePasswordUserDTO(String password){
        int length = password.length();
        if(length == 0){
            return false;
        }else if(length>20){
            return false;
        }
        return true;
    }

    private boolean validateLoginUserDTO(String name){
        if (checkIntString(name)){
            return false;
        }else if(name.length() < 3){
            return false;
        }
        return true;
    }

    private boolean validateSurnameUserDTO(String surname){
        if (checkIntString(surname)){
            return false;
        }else if(surname.length() < 3){
            return false;
        }
        return true;
    }

    private boolean validateEmailUserDTO(String email){
        if(!email.contains("@")){
            return false;
        }else if(email.length() < 4 || email.length() > 20){
            return false;
        }
        return true;

    }

    private static boolean checkIntString(String sequence){
        char[] chars = sequence.toCharArray();

        for (char ch : chars) {
            if (Character.isDigit(ch)) {
                return true;
            }
        }
        return false;

    }
}
