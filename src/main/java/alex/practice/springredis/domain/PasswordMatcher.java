package alex.practice.springredis.domain;

@FunctionalInterface
public interface PasswordMatcher {

    boolean match(final String password);
}
