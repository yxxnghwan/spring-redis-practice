package alex.practice.springredis.domain;

import java.time.LocalDateTime;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

@Getter
@RedisHash(value = "refreshToken", timeToLive = 60) // 만료 단위: 초
public class RefreshToken {

    @Id
    private String id;

    @Indexed
    private Long memberId;

    private LocalDateTime createdAt;

    public RefreshToken(final Long memberId, final LocalDateTime createdAt) {
        this.memberId = memberId;
        this.createdAt = createdAt;
    }
}
