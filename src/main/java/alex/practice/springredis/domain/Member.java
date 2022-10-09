package alex.practice.springredis.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity(name = "member")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    private String name;

    @Column(unique = true)
    private String account;
    private String password;

    public Member(final String name, final String account, final String password) {
        this.name = name;
        this.account = account;
        this.password = password;
    }

    public boolean matchPassword(final PasswordMatcher passwordMatcher) {
        return passwordMatcher.match(password);
    }
}
