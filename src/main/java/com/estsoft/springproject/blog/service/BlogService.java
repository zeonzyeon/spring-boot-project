package com.estsoft.springproject.blog.service;

import com.estsoft.springproject.blog.domain.dto.AddArticleRequest;
import com.estsoft.springproject.blog.domain.Article;
import com.estsoft.springproject.blog.domain.dto.UpdateArticleRequest;
import com.estsoft.springproject.blog.repository.BlogRepository;
import jakarta.transaction.Transactional;
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

    @Transactional
    public Article update(Long id, UpdateArticleRequest request) {
        // id로 Article 찾기 (존재하지 않으면 예외 발생 가능)
        Article article = findBy(id);

        // 제목과 내용을 업데이트
        article.update(request.getTitle(), request.getContent());

        // 변경된 Article 리턴
        return article;
    }
}
