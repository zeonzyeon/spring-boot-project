package com.estsoft.springproject.blog.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// view 구성을 위한 DTO

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ArticleViewResponse {
    private Long id;
    private String title;
    private String content;
}