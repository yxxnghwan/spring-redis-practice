package alex.practice.springredis.ui.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class MemberCreationRequest {

    private final String name;
    private final String account;
    private final String password;
}
