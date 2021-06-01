package com.example.lab1.service;

import com.example.lab1.beans.Tag;
import com.example.lab1.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class TagRepositoryService {

    @Autowired
    private TagRepository tagRepository;



    public Set<Tag> getAllTagsByQuery(Set<String> queryTags){
        Set<Tag> resultSet = new HashSet<>();
        for (String name: queryTags) {
                Tag tagFromRepository = tagRepository.findByName(name);
                if (tagFromRepository == null){
                    tagFromRepository = new Tag();
                    tagFromRepository.setName(name);
                    System.out.println(name);
                    tagRepository.save(tagFromRepository);
                }
                resultSet.add(tagFromRepository);
        }
        return resultSet;
    }
}
