package com.estsoft.springproject.blog.controller;

import com.estsoft.springproject.blog.domain.Article;
import com.estsoft.springproject.blog.domain.dto.AddArticleRequest;
import com.estsoft.springproject.blog.service.BlogService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class BlogControllerTest2 {
	@InjectMocks
	BlogController blogController;

	@MockBean
	BlogService blogService;

	MockMvc mockMvc;

	@BeforeEach
	void setUp() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(blogController).build();
	}

	@DisplayName("블로그 Article 생성")
	@Test
	void testArticle() throws Exception {
		// given
		AddArticleRequest request = new AddArticleRequest("title", "content");
		ObjectMapper objectMapper = new ObjectMapper();
		String requestJson = objectMapper.writeValueAsString(request);

		Mockito.doReturn(new Article("title", "content"))
			.when(blogService).saveArticle(any(AddArticleRequest.class));

		// when
		ResultActions actions = mockMvc.perform(post("/api/articles")
			.content(requestJson)
			.contentType(MediaType.APPLICATION_JSON_VALUE));

		// then
		actions.andExpect(status().isCreated())
			.andExpect(jsonPath("content").value(request.getContent()))
			.andExpect(jsonPath("title").value(request.getTitle()));
	}

	@Test
	void testDelete() throws Exception {
		// given
		Long id = 1L;

		// when
		ResultActions resultActions = mockMvc.perform(delete("/articles/{id}", id));

		// then
		// 1. HTTP 상태 코드 200 확인
		resultActions.andExpect(status().isOk());

		// 2. 서비스의 deleteBy 메서드가 한 번 호출되었는지 확인
		verify(blogService, times(1)).deleteBy(id);
	}

	// GET /articles/{id} : 블로그 게시글 단건 조회
	@Test
	public void testFindOne() throws Exception {
		// given
		Long id = 1L;

		// stubing : articles 객체를 만들어준다.
		Mockito.doReturn(new Article("title", "content"))
			.when(blogService).findBy(id);

		// when
		ResultActions resultActions = mockMvc.perform(get("/api/articles/{id}",id));

		// then
		resultActions.andExpect(status().isOk())
			.andExpect(jsonPath("title").value("title"))
			.andExpect(jsonPath("content").value("content"));
	}
}