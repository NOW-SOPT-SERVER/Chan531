package org.sopt.practice.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@Entity
@Getter
@NoArgsConstructor
public class Posting extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String content;

    private long blogId;

    @Builder(access = PRIVATE)
    public Posting(String title, String content, Blog blog) {
        this.title = title;
        this.content = content;
        this.blogId = blog.getId();
    }

    public static Posting of(String title, String content, Blog blog) {
        return Posting.builder()
                .title(title)
                .content(content)
                .blog(blog)
                .build();
    }
}
