package com.estsoft.springproject.book;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.estsoft.springproject.repository.MemberRepository;

@SpringBootTest
@AutoConfigureMockMvc
public class BookControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext context;

	@Autowired
	private MemberRepository memberRepository;

	@BeforeEach
	public void mockMvcSetUp() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}

	@Test
	public void testGetAllBooks() throws Exception {
		// given:

		// when:
		ResultActions resultAction = mockMvc.perform(get("/books"));

		// then:
		resultAction.andExpect(status().isOk())
			.andExpect(view().name("bookManagement"))
			.andExpect(model().attribute("bookList", hasSize(2)));
	}

	@AfterEach
	public void cleanUp() {
		memberRepository.deleteAll();
	}
}
