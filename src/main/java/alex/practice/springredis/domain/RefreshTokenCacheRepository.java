package alex.practice.springredis.domain;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface RefreshTokenCacheRepository extends CrudRepository<RefreshToken, String> {

    List<RefreshToken> findByMemberId(final Long memberId);
}
