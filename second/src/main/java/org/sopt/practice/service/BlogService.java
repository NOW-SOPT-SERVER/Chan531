package org.sopt.practice.service;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.sopt.practice.domain.Blog;
import org.sopt.practice.domain.Member;
import org.sopt.practice.dto.request.BlogCreationRequest;
import org.sopt.practice.dto.request.BlogTitleUpdateRequest;
import org.sopt.practice.dto.response.BlogCreationResponse;
import org.sopt.practice.exception.BlogException;
import org.sopt.practice.exception.MemberException;
import org.sopt.practice.repository.BlogRepository;
import org.sopt.practice.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static org.sopt.practice.common.message.ErrorMessage.INVALID_BLOG;
import static org.sopt.practice.common.message.ErrorMessage.INVALID_MEMBER;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BlogService {

    private final MemberRepository memberRepository;
    private final BlogRepository blogRepository;

    @Transactional
    public String createBlog(long memberId, BlogCreationRequest request) {
        val member = findMember(memberId);
        val blog = generateBlog(request, member);
        saveBlog(blog);
        return blog.getId().toString();
    }

    @Transactional
    public void updateTitle(long blogId, BlogTitleUpdateRequest request) {
        val blog = findBlog(blogId);
        blog.updateTitle(request.title());
    }

    private Member findMember(long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(INVALID_MEMBER));
    }

    private Blog generateBlog(BlogCreationRequest request, Member member) {
        val blog = Blog.of(request.title(), request.description(), member);
        return blog;
    }

    private void saveBlog(Blog blog) {
        blogRepository.save(blog);
    }

    private Blog findBlog(long blogId) {
        return blogRepository.findById(blogId)
                .orElseThrow(() -> new BlogException(INVALID_BLOG));
    }
}
