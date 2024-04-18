package org.sopt.practice.service;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.sopt.practice.dto.request.MemberCreationRequest;
import org.sopt.practice.domain.Member;
import org.sopt.practice.dto.response.MemberCreationResponse;
import org.sopt.practice.dto.response.MemberListResponse;
import org.sopt.practice.dto.response.MemberProfileResponse;
import org.sopt.practice.exception.MemberException;
import org.sopt.practice.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.sopt.practice.message.ErrorMessage.INVALID_MEMBER;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public MemberCreationResponse createMember(MemberCreationRequest request) {
        val member = generateMember(request);
        saveMember(member);
        val response = MemberCreationResponse.of(member);
        return response;
    }

    public MemberProfileResponse getMember(Long memberId) {
        val member = findMember(memberId);
        val response = MemberProfileResponse.of(member);
        return response;
    }

    @Transactional
    public void deleteMember(Long memberId) {
        val member = findMember(memberId);
        memberRepository.delete(member);
    }

    public MemberListResponse getMembers() {
        val members = getMemberList();
        val response = MemberListResponse.of(members);
        return response;
    }

    private Member generateMember(MemberCreationRequest request) {
        val member = Member.of(request.name(), request.part(), request.age());
        return member;
    }

    private void saveMember(Member member) {
        memberRepository.save(member);
    }

    private Member findMember(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(INVALID_MEMBER));
    }

    private List<Member> getMemberList() {
        return memberRepository.findAll();
    }
}
