package alex.practice.springredis.application;

import alex.practice.springredis.application.dto.LoginTokenDto;
import alex.practice.springredis.domain.Member;
import alex.practice.springredis.domain.MemberRepository;
import alex.practice.springredis.domain.RefreshToken;
import alex.practice.springredis.domain.RefreshTokenCacheRepository;
import alex.practice.springredis.support.JwtTokenProvider;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthService {

    private final MemberRepository memberRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final RefreshTokenCacheRepository refreshTokenCacheRepository;

    public LoginTokenDto login(final String account, final String password) {
        final Member member = memberRepository.findByAccount(account)
                .orElseThrow();
        if (!member.matchPassword(pw -> pw.equals(password))) {
            throw new IllegalArgumentException();
        }

        deleteOverThree(member.getId());

        final RefreshToken refreshToken
                = new RefreshToken(member.getId(), LocalDateTime.now());
        refreshTokenCacheRepository.save(refreshToken);

        return new LoginTokenDto(
                jwtTokenProvider.createToken(member.getId().toString()),
                refreshToken.getId(),
                member.getId()
        );
    }

    private void deleteOverThree(final Long memberId) {
        final List<RefreshToken> memberRefreshTokens = refreshTokenCacheRepository.findByMemberId(memberId);
        if (memberRefreshTokens.size() >= 3) {
            final RefreshToken deletedRefreshToken = memberRefreshTokens.stream()
                    .min(Comparator.comparing(RefreshToken::getCreatedAt))
                    .orElseThrow();
            refreshTokenCacheRepository.deleteById(deletedRefreshToken.getId());
        }
    }
}
