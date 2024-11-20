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
	private MemberRepository memberRepository;

    @BeforeEach
    public void mockMvcSetUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @DisplayName("멤버 목록 조회에 성공한다")
    @Test
    public void testGetAllMembers() throws Exception {
        // given: 멤버 저장
        Member savedMember = memberRepository.save(new Member(1L, "홍길동")); // 데이터 저장

        // when: GET /members 호출
        ResultActions resultActions = mockMvc.perform(get("/members") // URL 호출
            .accept(MediaType.APPLICATION_JSON));

        // then: 응답 검증
        resultActions.andExpect(status().isOk()) // HTTP 200 상태 확인
            .andExpect(jsonPath("$[0].id").value(savedMember.getId())) // 저장된 데이터와 비교
            .andExpect(jsonPath("$[0].name").value(savedMember.getName())); // 이름 확인
    }

    @AfterEach
    public void cleanUp() {
        memberRepository.deleteAll();
    }
}