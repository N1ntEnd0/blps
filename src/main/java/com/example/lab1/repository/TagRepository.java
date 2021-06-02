package com.example.lab1.repository;

import com.example.lab1.beans.Tag;
import org.springframework.data.repository.CrudRepository;

public interface TagRepository extends CrudRepository<Tag, Long> {
    Tag findByName(String name);
}
