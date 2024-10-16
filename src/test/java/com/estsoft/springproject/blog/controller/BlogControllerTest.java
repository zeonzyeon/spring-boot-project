package com.estsoft.springproject.blog.controller;

import com.estsoft.springproject.blog.domain.dto.AddArticleRequest;
import com.estsoft.springproject.blog.domain.Article;
import com.estsoft.springproject.blog.domain.dto.UpdateArticleRequest;
import com.estsoft.springproject.blog.repository.BlogRepository;
import com.estsoft.springproject.blog.service.BlogService;
import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.jupiter.api.Assertions;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.data.jpa.domain.AbstractPersistable_.id;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
class BlogControllerTest {
    @Autowired
    private WebApplicationContext context;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private BlogRepository repository;

    @Autowired
    private BlogService service;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    // POST /articles API 테스트
    @Test
    public void addArticle() throws Exception {
        // given: article 저장
        AddArticleRequest request = new AddArticleRequest("제목", "내용");

        // 직렬화  (object -> json)
        String json = objectMapper.writeValueAsString(request);

        // when: POST /articles API 호출
        ResultActions resultActions = mockMvc.perform(post("/articles")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json));

        // then: 호출 결과 검증
        resultActions.andExpect(status().isCreated())   // json
                .andExpect(jsonPath("$.title").value(request.getTitle()))
                .andExpect(jsonPath("$.content").value(request.getContent()));

        List<Article> articleList = repository.findAll();
        assertThat(articleList.size()).isEqualTo(1);
    }

    @Test
    public void testFindAll() throws Exception {
        // given
        final String url = "/api/articles";
        final String title = "제목";
        final String content = "내용";
        Article article = repository.save(new Article(title, content));

        // when
        ResultActions resultActions = mockMvc.perform(get("/articles")
                .accept(MediaType.APPLICATION_JSON));

        // then : 정상적으로 요청이 되었는지 검증
        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(id))  // 배열 내 첫 번째 요소의 id 확인
                .andExpect(jsonPath("$[0].title").value(article.getTitle()))  // 배열 내 첫 번째 요소의 title 확인
                .andExpect(jsonPath("$[0].content").value(article.getContent()));  // 배열 내 첫 번째 요소의 content 확인
    }

    // 단건 조회 API - id에 해당하는 자원이 없을 경우 (4xx 예외)
    @Test
    public void findOneException() throws Exception {
        // when: API 호출
        ResultActions resultActions = mockMvc.perform(get("/articles/{id}", 1L) // 존재하지 않는 id 999L 사용
                .accept(MediaType.APPLICATION_JSON));

        // then: Exception 검증, resultAction STATUS CODE 검증
        resultActions.andExpect(status().isBadRequest());
        ;
        assertThrows(IllegalArgumentException.class, () -> service.findBy(1L));
    }

    // todo 블로그 글 삭제 API 호출 테스트
    // 글 정보 insert, 삭제 Api 호출, (STATUS CODE 검증), respository.findAll()
    @Test
    public void deleteTest() throws Exception {
        // given: 테스트할 글 저장
        Article article = repository.save(new Article("blog title", "blog content"));
        Long id = article.getId();

        // when: 글 삭제 API 호출
        ResultActions resultActions = mockMvc.perform(delete("/articles/{id}", id));

        // then: 상태 코드가 200 OK 인지 확인
        resultActions.andExpect(status().isOk());

        // 저장된 글이 모두 삭제되었는지 확인
        List<Article> articleList = repository.findAll();
        Assertions.assertThat(articleList).isEmpty();  // 저장된 글이 없는지 검증
    }

    // PUT /articles/{id} body(json content) 요청
    @Test
    public void updateArticle() throws Exception {
        Article article = repository.save(new Article("blog title", "blog content"));

        // 수정할 데이터(object) -> json
        UpdateArticleRequest request = new UpdateArticleRequest("변경 제목","변경 내용");
        String updateJsonContent = objectMapper.writeValueAsString(request);

        ResultActions resultActions = mockMvc.perform(put("/articles/{id}", article.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(updateJsonContent)
        );

        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value(request.getTitle()));
    }

    // 수정 API 호출시 예외 발생했을 경우 = status code 4xx, 예외 검증
    @Test
    public void updateArticleException() throws Exception {
        // 존재하지 않는 id로 수정 요청 (예: 999L)
        Long invalidId = 999L;

        // 수정할 데이터(object) -> json
        UpdateArticleRequest request = new UpdateArticleRequest("title", "content");
        String reqeustBody = objectMapper.writeValueAsString(request);

        // when: 존재하지 않는 id로 수정 요청
        ResultActions resultActions = mockMvc.perform(put("/articles/{id}", invalidId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(reqeustBody));

        // then: 상태 코드가 4xx (예: 404 Not Found) 인지 확인
        resultActions.andExpect(status().is4xxClientError()); // 404 상태 코드 확인

        // 서비스 레이어에서 예외가 발생했는지 확인 (IllegalArgumentException 혹은 CustomException)
        assertThrows(IllegalArgumentException.class, () -> service.update(invalidId, request));
    }
}
