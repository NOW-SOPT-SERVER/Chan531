package org.sopt.practice.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

    public void updateTitle(String title) {
        this.title = title;
    }
}
