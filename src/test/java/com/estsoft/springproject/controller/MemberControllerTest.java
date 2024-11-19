package com.estsoft.springproject.controller;

import com.estsoft.springproject.entity.Member;
import com.estsoft.springproject.repository.MemberRepository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest // 테스트용 어플리케이션 컨텍스트 생성
@AutoConfigureMockMvc // MockMvc 생성 및 자동 구성
class MemberControllerTest {
    @Autowired
    WebApplicationContext context;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    MemberRepository repository;
	@Autowired
	private MemberRepository memberRepository;

    @BeforeEach
    public void mockMvcSetUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @DisplayName("멤버 목록 조회에 성공한다")
    @Test
    public void testGetAllMembers() throws Exception {
        // given: 멤버 목록 저장
        String url = "/members";
        Member savedMember = memberRepository.save(new Member(1L, "홍길동"));

        // when:    GET /members
        ResultActions resultActions = mockMvc.perform(get(url) // 1
                .accept(MediaType.APPLICATION_JSON)); // 2

        // then:    response 검증
        resultActions.andExpect(status().is2xxSuccessful()) // 3
            // 4. 응답의 0번째 값이 DB에 저장한 값고 같은지 검증
            //     .andExpect(jsonPath("$[0].id").value(1))
            //     .andExpect(jsonPath("$[1].id").value(2))
            .andExpect(jsonPath("$[0].id").value(savedMember.getId()))
            .andExpect(jsonPath("$[0].name").value(savedMember.getName()));
        ;
    }

    @AfterEach
    public void cleanUp() {
        memberRepository.deleteAll();
    }
}