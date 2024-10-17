package com.estsoft.springproject.blog.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import java.util.List;

@Slf4j
@RestController
public class ExternalApiController {

    @GetMapping("/api/external")
    public String callApi() {
        // 외부 API 호출
        RestTemplate restTemplate = new RestTemplate();

        String url = "https://jsonplaceholder.typicode.com/posts";

        // 외부 API 호출, 역직렬화 (restTemplate)
        ResponseEntity<List<Content>> resultList =
                restTemplate.exchange(url, HttpMethod.GET, null,
                        new ParameterizedTypeReference<List<Content>>() {
                        });

        // API 응답 처리 (결과 출력)
        log.info("API Response: {}", resultList.getBody());

        return "OK";
    }
}
