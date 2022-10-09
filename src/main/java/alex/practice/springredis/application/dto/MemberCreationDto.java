package alex.practice.springredis.application.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class MemberCreationDto {

    private final String name;
    private final String account;
    private final String password;
}
