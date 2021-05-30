package com.example.lab1.configurations;

import com.example.lab1.beans.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
public class StackOverflowConfiguration extends WebMvcConfigurerAdapter {

    @Bean
    @Scope(scopeName = "prototype")
    public User getUser() {return new User();}

    @Bean
    @Scope(scopeName = "prototype")
    public Answer getAnswer() {return new Answer();}

    @Bean
    @Scope(scopeName = "prototype")
    public Question getQuestion() {return new Question();}

    @Bean
    @Scope(scopeName = "prototype")
    public Tag getTags() {return new Tag();}

}
