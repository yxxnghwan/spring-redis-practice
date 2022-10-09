package alex.practice.springredis.application;

import alex.practice.springredis.application.dto.MemberCreationDto;
import alex.practice.springredis.application.dto.MemberDto;
import alex.practice.springredis.domain.Member;
import alex.practice.springredis.domain.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public Long joinMember(final MemberCreationDto memberCreationDto) {
        final Member savedMember = memberRepository.save(
                new Member(
                        memberCreationDto.getName(),
                        memberCreationDto.getAccount(),
                        memberCreationDto.getPassword()
                )
        );

        return savedMember.getId();
    }

    public MemberDto findMember(final Long id) {
        final Member member = memberRepository.findById(id)
                .orElseThrow();
        return new MemberDto(member.getId(), member.getName(), member.getAccount(), member.getPassword());
    }
}
