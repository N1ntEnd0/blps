package com.example.lab1.service;

import com.example.lab1.DTO.ResponseMessageDTO;
import com.example.lab1.DTO.TokenObject;
import com.example.lab1.DTO.UserDTO;
import com.example.lab1.beans.User;
import com.example.lab1.exceptions.UserNotFoundException;
import com.example.lab1.repository.UserRepository;
import com.example.lab1.utils.JWTutils;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;


@Service
public class UserRepositoryService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JWTutils jwTutils;
    @Autowired
    private User user;
    @Autowired
    private DTOConverter dtoConverter;
//    @Autowired
//    private RoleRepository roleRepository;

//    Logger logger = LogManager.getLogger(UserRepositoryService.class);

    public UserRepository getUserRepository() {
        return userRepository;
    }

    public User getUserFromRequest(HttpServletRequest request) throws UserNotFoundException {
        String token = jwTutils.getTokenFromRequest(request);
        String email = jwTutils.getLoginFromToken(token);
        try {
            User user = this.findByEmail(email);
//            logger.log(Level.INFO, "getting user from request" + user.getEmail());
            return user;
        }catch (UserNotFoundException e){
            throw e;
        }
    }

    public ResponseEntity<ResponseMessageDTO> registerUserDTO(UserDTO userDTO){
        ResponseMessageDTO message = new ResponseMessageDTO();
        user = dtoConverter.convertUserFromDTO(userDTO);
        String answerText = "";
        try{
            this.save(user);
            message.setAnswer("Вы были успешно зарегестрированы");
        }catch (DataIntegrityViolationException e){
            if(e.getCause().getClass() == ConstraintViolationException.class){
                answerText = "Пользователь с таким email уже существует";
            }else{
                answerText = "УПС! Произошла ошибка, пожалуйста, попробуйте позднее";
            }
            message.setAnswer(answerText);
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(message, HttpStatus.OK);
    }


    public ResponseEntity<ResponseMessageDTO> authUserDTO(UserDTO userDTO){
        ResponseMessageDTO message = new ResponseMessageDTO();
        try {
            user = this.findByLoginAndPassword(userDTO.getLogin(), userDTO.getPassword());
            TokenObject token = new TokenObject(jwTutils.generateToken(user.getMail()));
            message.setAnswer(token.getToken());
            return new ResponseEntity<>(message, HttpStatus.ACCEPTED);
        }catch (UserNotFoundException e){
            message.setAnswer(e.getErrMessage());
            return new ResponseEntity<>(message, e.getErrStatus());
        }
    }

    public void save(User user)  {
//        user.setRoles(Collections.singleton(new Role(1L, "ROLE_ADMIN")));
        this.userRepository.save(user);
    }

    public User findById(Long id) throws UserNotFoundException{
        User user = this.userRepository.findById(id).get();
        if(user == null){
            throw new UserNotFoundException("Пользователь с таким email не найден", HttpStatus.BAD_REQUEST);
        }
        return user;
    }


    public User findByLoginAndPassword(String login, String password) throws UserNotFoundException{
        User user  = this.userRepository.findByLoginAndPassword(login, password);
        if(user == null){
            throw new UserNotFoundException("Пользователь с таким email не найден", HttpStatus.BAD_REQUEST);
        }
        return user;
    }

    public User findByEmail(String email) throws UserNotFoundException{
        User user = this.userRepository.findByMail(email);
        if(user == null){
            throw new UserNotFoundException("Пользователь с таким email не найден. Ошибка авторизации", HttpStatus.UNAUTHORIZED);
        }
        return user;
    }
}
