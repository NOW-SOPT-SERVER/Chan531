package org.sopt.practice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.sopt.practice.common.dto.Response;
import org.sopt.practice.dto.request.BlogCreationRequest;
import org.sopt.practice.dto.request.BlogTitleUpdateRequest;
import org.sopt.practice.service.BlogService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

import static org.sopt.practice.common.dto.Response.*;
import static org.sopt.practice.common.message.SuccessMessage.SUCCESS_CREATE_BLOG;
import static org.sopt.practice.common.message.SuccessMessage.SUCCESS_UPDATE_TITLE;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/blogs")
public class BlogController {

    private final BlogService blogService;

    @PostMapping
    public ResponseEntity<Response> createBlog(@RequestHeader Long memberId, @RequestBody BlogCreationRequest request) {
        val response = blogService.createBlog(memberId, request);
        return ResponseEntity.created(getURI())
                .header("Blog-Id", response)
                .body(success(SUCCESS_CREATE_BLOG.getMessage()));
    }

    @PatchMapping("/{blogId}/title")
    public ResponseEntity<Response> updateTitle(@PathVariable Long blogId, @Valid @RequestBody BlogTitleUpdateRequest request) {
        blogService.updateTitle(blogId, request);
        return ResponseEntity.ok(success(SUCCESS_UPDATE_TITLE.getMessage()));
    }

    private URI getURI() {
        return ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/")
                .buildAndExpand()
                .toUri();
    }
}
