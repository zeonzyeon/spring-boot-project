package com.estsoft.springproject.blog.controller;

import com.estsoft.springproject.blog.domain.dto.AddArticleRequest;
import com.estsoft.springproject.blog.domain.Article;
import com.estsoft.springproject.blog.domain.dto.ArticleResponse;
import com.estsoft.springproject.blog.domain.dto.UpdateArticleRequest;
import com.estsoft.springproject.blog.service.BlogService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api")
@Tag(name = "블로그 저장/수정/삭제/조회용 API", description = "API 설명을 이곳에 작성하면 됩니다")
public class BlogController {
    private final BlogService service;

    public BlogController(BlogService service) {
        this.service = service;
    }

    //    @RequestMapping(method = RequestMethod.POST, value = "/articles")
    @PostMapping("/articles")
    public ResponseEntity<ArticleResponse> writeArticle(@RequestBody AddArticleRequest request) {
        log.info("request: {}, {}", request.getTitle(), request.getContent());
        Article article = service.saveArticle(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(article.convert());
    }

    // Request Mapping      조회: HTTP METHOD
    @GetMapping("/articles")
    public ResponseEntity<List<ArticleResponse>> finalAll() {
        List<Article> articleList = service.findAll();
        // List<Article> -> List<ArticleResponse> 형태로 변환해서 응답으로 보내기
        List<ArticleResponse> list = articleList.stream()
                .map(article -> new ArticleResponse(article.getId(), article.getTitle(), article.getContent()))
                .toList();
        return ResponseEntity.ok(list);
    }

    // 단건 조회 API (Request mapping) 만들기 GET /articles/{id}
    @GetMapping("/articles/{id}")
    public ResponseEntity<ArticleResponse> findById(@PathVariable Long id) {
        Article article = service.findBy(id);  // Exception 발생 시 5xx 서버 오류 대신 4xx 상태 코드로 처리
        return ResponseEntity.ok(article.convert());
    }

    // DELETE /articles/{id}
    @DeleteMapping("/articles/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        service.deleteBy(id);
        return ResponseEntity.ok().build();
    }

    // /articles/{id} 수정 API request body
    @PutMapping("/articles/{id}")
    public ResponseEntity<ArticleResponse> updateById(@PathVariable Long id, @RequestBody UpdateArticleRequest request) {
        Article updateArticle = service.update(id, request);
        return ResponseEntity.ok(updateArticle.convert());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage()); // reason : ""
    }
}