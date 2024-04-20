package org.sopt.practice.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.sopt.practice.exception.BlogException;

import java.util.Objects;

import static org.sopt.practice.common.message.ErrorMessage.EXIST_BLOG;
import static org.sopt.practice.common.message.ErrorMessage.EXIST_MEMBER;

@Entity
@Getter
@NoArgsConstructor
public class Member extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private Part part;

    private int age;

    @OneToOne
    private Blog blog;

    @Builder
    private Member(String name, Part part, int age) {
        this.name = name;
        this.part = part;
        this.age = age;
    }

    public static Member of(String name, Part part, int age) {
        return Member.builder()
                .name(name)
                .part(part)
                .age(age)
                .build();
    }

    public void initBlog(Blog blog) {
        if (Objects.nonNull(this.blog)) {
            throw new BlogException(EXIST_BLOG);
        }
        this.blog = blog;
    }
}
