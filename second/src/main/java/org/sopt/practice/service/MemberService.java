package org.sopt.practice.service;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.sopt.practice.common.jwt.JwtTokenProvider;
import org.sopt.practice.common.redis.Token;
import org.sopt.practice.common.redis.TokenRepository;
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

import static org.sopt.practice.common.message.ErrorMessage.INVALID_MEMBER;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private static final long ACCESS_TOKEN_EXPIRATION_TIME = 60 * 60 * 5 * 1000L;
    private static final long REFRESH_TOKEN_EXPIRATION_TIME = 60 * 60 * 24 * 1000L * 14;

    private final MemberRepository memberRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final TokenRepository tokenRepository;

    @Transactional
    public MemberCreationResponse createMember(MemberCreationRequest request) {
        val member = generateMember(request);
        saveMember(member);
        val memberId = member.getId();
        val accessToken = getAccessToken(memberId);
        val refreshToken = getRefreshToken(memberId);
        saveRefreshToken(memberId, refreshToken);
        val response = MemberCreationResponse.of(accessToken, refreshToken, memberId.toString());
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

    private String getAccessToken(long memberId) {
        val accessToken = jwtTokenProvider.createToken(memberId, ACCESS_TOKEN_EXPIRATION_TIME);
        return accessToken;
    }

    private String getRefreshToken(long memberId) {
        val refreshToken = jwtTokenProvider.createToken(memberId, REFRESH_TOKEN_EXPIRATION_TIME);
        return refreshToken;
    }

    private void saveRefreshToken(long memberId, String refreshToken) {
        val token = Token.of(memberId, refreshToken);
        tokenRepository.save(token);
    }

    private Member findMember(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(INVALID_MEMBER));
    }

    private List<Member> getMemberList() {
        return memberRepository.findAll();
    }
}
