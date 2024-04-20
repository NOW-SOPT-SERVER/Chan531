package org.sopt.practice.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.sopt.practice.exception.BlogException;

import java.util.Objects;

import static org.sopt.practice.common.message.ErrorMessage.*;

@Entity
@Getter
@NoArgsConstructor
public class Blog extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 200)
    private String title;

    private String description;

    @OneToOne(fetch = FetchType.LAZY)
    private Member member;

    @Builder
    public Blog(String title, String description, Member member) {
        this.title = title;
        this.description = description;
        this.member = member;
    }

    public static Blog of(String title, String description, Member member) {
        return Blog.builder()
                .title(title)
                .description(description)
                .member(member)
                .build();
    }

    private Member setMember(Member member) {
        if (Objects.nonNull(this.member)) {
            throw new BlogException(EXIST_MEMBER);
        }
        member.initBlog(this);
        return member;
    }

    public void updateTitle(String title) {
        this.title = title;
    }
}
