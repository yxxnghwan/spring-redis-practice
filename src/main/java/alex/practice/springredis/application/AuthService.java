package alex.practice.springredis.application;

import alex.practice.springredis.application.dto.LoginTokenDto;
import alex.practice.springredis.domain.Member;
import alex.practice.springredis.domain.MemberRepository;
import alex.practice.springredis.support.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthService {

    private final MemberRepository memberRepository;
    private final JwtTokenProvider jwtTokenProvider;

    public LoginTokenDto login(final String account, final String password) {
        final Member member = memberRepository.findByAccount(account)
                .orElseThrow();
        if (!member.matchPassword(pw -> pw.equals(password))) {
            throw new IllegalArgumentException();
        }
        return new LoginTokenDto(jwtTokenProvider.createToken(member.getId().toString()), member.getId());
    }
}
