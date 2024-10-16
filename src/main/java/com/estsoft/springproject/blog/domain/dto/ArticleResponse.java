package com.estsoft.springproject.blog.domain.dto;

import jdk.jshell.Snippet;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ArticleResponse {
    private Long id;
    private String title;
    private String content;

    // Builder 메서드 추가
    public static Builder builder() {
        return new Builder();
    }

    // 내부 Builder 클래스
    public static class Builder {
        private Long id;
        private String title;
        private String content;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder content(String content) {
            this.content = content;
            return this;
        }

        public ArticleResponse build() {
            return new ArticleResponse(id, title, content);
        }
    }
}
