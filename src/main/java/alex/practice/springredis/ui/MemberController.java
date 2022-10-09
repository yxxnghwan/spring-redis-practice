package alex.practice.springredis.ui;

import alex.practice.springredis.application.AuthService;
import alex.practice.springredis.application.MemberService;
import alex.practice.springredis.application.dto.LoginTokenDto;
import alex.practice.springredis.application.dto.MemberCreationDto;
import alex.practice.springredis.application.dto.MemberDto;
import alex.practice.springredis.ui.dto.LoginMemberRequest;
import alex.practice.springredis.ui.dto.LoginRequest;
import alex.practice.springredis.ui.dto.MemberCreationRequest;
import java.net.URI;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    @PostMapping
    public ResponseEntity<Void> createMember(@RequestBody @Valid final MemberCreationRequest request) {
        final Long memberId = memberService.joinMember(new MemberCreationDto(
                request.getName(),
                request.getAccount(),
                request.getPassword()
        ));
        return ResponseEntity.created(URI.create("/members/" + memberId)).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MemberDto> findMember(@PathVariable("id") Long id) {
        final MemberDto member = memberService.findMember(id);
        return ResponseEntity.ok(member);
    }

    @GetMapping("/me")
    public ResponseEntity<MemberDto> findMe(@AuthenticationPrincipal final LoginMemberRequest loginMemberRequest) {
        final MemberDto member = memberService.findMember(loginMemberRequest.getId());
        return ResponseEntity.ok(member);
    }
}
