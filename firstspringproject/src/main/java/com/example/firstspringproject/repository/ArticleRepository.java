package com.example.firstspringproject.repository;

import com.example.firstspringproject.entitiy.Article;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ArticleRepository extends CrudRepository<Article,Long> {
    @Override
    List<Article> findAll();
}
