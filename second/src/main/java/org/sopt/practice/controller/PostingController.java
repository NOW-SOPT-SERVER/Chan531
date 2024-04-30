package org.sopt.practice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.sopt.practice.common.dto.SuccessResponse;
import org.sopt.practice.dto.request.PostingCreationRequest;
import org.sopt.practice.service.PostingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

import static org.sopt.practice.common.dto.SuccessResponse.*;
import static org.sopt.practice.common.message.SuccessMessage.SUCCESS_CREATE_POSTING;
import static org.sopt.practice.common.message.SuccessMessage.SUCCESS_GET_POSTING;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1")
public class PostingController {

    private final PostingService postingService;

    @PostMapping("/blogs/{blogId}/postings")
    public ResponseEntity<SuccessResponse> createPosting(
            @RequestHeader Long memberId,
            @PathVariable Long blogId,
            @Valid @RequestBody PostingCreationRequest request
    ) {
        val response = postingService.createPosting(memberId, blogId, request);
        return ResponseEntity.created(getURI())
                .header("Post-Id", response)
                .body(of(SUCCESS_CREATE_POSTING.getMessage()));
    }

    @GetMapping("/blogs/{blogId}/postings/{postingId}")
    public ResponseEntity<SuccessResponse> getPosting(@PathVariable Long blogId, @PathVariable Long postingId) {
        val response = postingService.getPosting(blogId, postingId);
        return ResponseEntity.ok(of(SUCCESS_GET_POSTING.getMessage(), response));
    }

    private URI getURI() {
        return ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/")
                .buildAndExpand()
                .toUri();
    }
}
