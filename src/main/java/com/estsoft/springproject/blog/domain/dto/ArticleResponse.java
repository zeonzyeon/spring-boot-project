package com.estsoft.springproject.blog.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "블로그 조회 결과")
public class ArticleResponse {
    @Schema(description = "블로그 ID", type = "Long")
    private Long id;

    @Schema(description = "블로그 제목", type = "String")
    private String title;

    @Schema(description = "블로그 내용", type = "String")
    private String content;
}
