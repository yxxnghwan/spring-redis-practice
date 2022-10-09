package alex.practice.springredis.ui;

import alex.practice.springredis.application.AuthService;
import alex.practice.springredis.application.dto.LoginTokenDto;
import alex.practice.springredis.ui.dto.LoginRequest;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginTokenDto> login(@RequestBody @Valid final LoginRequest loginRequest) {
        final LoginTokenDto loginTokenDto = authService.login(loginRequest.getAccount(), loginRequest.getPassword());
        return ResponseEntity.ok(loginTokenDto);
    }
}
