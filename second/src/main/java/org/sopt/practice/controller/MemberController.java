package org.sopt.practice.controller;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.sopt.practice.common.dto.Response;
import org.sopt.practice.dto.request.MemberCreationRequest;
import org.sopt.practice.service.MemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

import static org.sopt.practice.common.dto.Response.*;
import static org.sopt.practice.common.message.SuccessMessage.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/members")
public class MemberController {

    private final MemberService memberService;

    @PostMapping
    public ResponseEntity<Response> createMember(@RequestBody MemberCreationRequest request) {
        val response = memberService.createMember(request);
        return ResponseEntity.created(getURI())
                .body(success(SUCCESS_CREATE_MEMBER.getMessage(), response));
    }

    @GetMapping("/{memberId}")
    public ResponseEntity<Response> getMember(@PathVariable Long memberId) {
        val response = memberService.getMember(memberId);
        return ResponseEntity.ok(success(SUCCESS_GET_MEMBER.getMessage(), response));
    }

    @DeleteMapping("/{memberId}")
    public ResponseEntity<Response> deleteMember(@PathVariable Long memberId) {
        memberService.deleteMember(memberId);
        return ResponseEntity.ok(success(SUCCESS_DELETE_MEMBER.getMessage()));
    }

    @GetMapping
    public ResponseEntity<Response> getMembers() {
        val response = memberService.getMembers();
        return ResponseEntity.ok(success(SUCCESS_GET_MEMBER_LIST.getMessage(), response));
    }

    private URI getURI() {
        return ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/")
                .buildAndExpand()
                .toUri();
    }
}
