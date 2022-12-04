package com.example.firstspringproject.dto;

import com.example.firstspringproject.entitiy.Article;
import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
public class articleForm {
    private Long id;
    private String title;
    private String content;




    public Article toEntity(){
        return new Article(id,title,content);
    }
}
