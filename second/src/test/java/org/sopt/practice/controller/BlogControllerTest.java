package org.sopt.practice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.sopt.practice.dto.request.BlogCreationRequest;
import org.sopt.practice.repository.BlogRepository;
import org.sopt.practice.repository.MemberRepository;
import org.sopt.practice.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BlogController.class)
@AutoConfigureMockMvc
class BlogControllerTest {

    @SpyBean
    private BlogService blogService;

    @MockBean
    private MemberRepository memberRepository;

    @MockBean
    private BlogRepository blogRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Nested
    class createBlog {

        @Test
        @DisplayName("존재하지 않는 멤버의 id를 헤더에 입력하면 블로그 생성에 실패한다.")
        public void createBlogFail() throws Exception {
            String request = objectMapper.writeValueAsString(new BlogCreationRequest("찬이네 블로그", "찬이네 블로그입니다."));

            mockMvc.perform(
                            post("/api/v1/blogs")
                                    .content(request).header("memberId", 2)
                                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isNotFound())
                    .andDo(print());
        }
    }

}
