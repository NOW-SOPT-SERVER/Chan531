package org.sopt.practice.service;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.sopt.practice.domain.Blog;
import org.sopt.practice.domain.Member;
import org.sopt.practice.domain.Posting;
import org.sopt.practice.dto.request.PostingCreationRequest;
import org.sopt.practice.dto.response.PostingInfoResponse;
import org.sopt.practice.exception.BlogException;
import org.sopt.practice.exception.MemberException;
import org.sopt.practice.exception.PostingException;
import org.sopt.practice.repository.BlogRepository;
import org.sopt.practice.repository.MemberRepository;
import org.sopt.practice.repository.PostingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static org.sopt.practice.common.message.ErrorMessage.*;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostingService {

    private final MemberRepository memberRepository;
    private final BlogRepository blogRepository;
    private final PostingRepository postingRepository;

    @Transactional
    public String createPosting(long memberId, long blogId, PostingCreationRequest request) {
        val member = findMember(memberId);
        val blog = findBlog(blogId);
        checkMemberBlog(member, blog);
        val posting = generatePosting(blog, request);
        savePosting(posting);
        val response = posting.getId().toString();
        return response;
    }

    public PostingInfoResponse getPosting(long blogId, long postingId) {
        val blog = findBlog(blogId);
        val posting = findPosting(postingId);
        checkBlogPosting(blog, posting);
        val response = PostingInfoResponse.of(posting);
        return response;
    }

    private Member findMember(long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(INVALID_MEMBER));
    }

    private Blog findBlog(long blogId) {
        return blogRepository.findById(blogId)
                .orElseThrow(() -> new BlogException(INVALID_BLOG));
    }

    private void checkMemberBlog(Member member, Blog blog) {
        if (!blog.getMember().equals(member)) {
            throw new BlogException(NOT_MATCH_MEMBER_BLOG);
        }
    }

    private Posting generatePosting(Blog blog, PostingCreationRequest request) {
        return Posting.of(request.title(), request.content(), blog);
    }

    private void savePosting(Posting posting) {
        postingRepository.save(posting);
    }

    private Posting findPosting(long postingId) {
        return postingRepository.findById(postingId)
                .orElseThrow(() -> new PostingException(INVALID_POSTING));
    }

    private void checkBlogPosting(Blog blog, Posting posting) {
        if (posting.getBlogId() != blog.getId()) {
            throw new PostingException(NOT_MATCH_BLOG_POSTING);
        }
    }
}
