package com.estsoft.springproject.blog.service;

import com.estsoft.springproject.blog.domain.dto.AddArticleRequest;
import com.estsoft.springproject.blog.domain.Article;
import com.estsoft.springproject.blog.repository.BlogRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogService {
    private final BlogRepository repository;

    public BlogService(BlogRepository repository) {
        this.repository = repository;
    }

    // blog 게시글 저장
    public Article saveArticle(AddArticleRequest request) {
        return repository.save(request.toEntity());
    }

    // blog 게시글 조화
    public List<Article> findAll() {
        return repository.findAll();
    }

    // blog 게시글 단건 조회
    public Article findBy(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found id :" + id));
    }

    // blog 게시글 삭제 (id)
    public void deleteBy(Long id) {
        repository.deleteById(id);
    }
}
