package pl.lostworld.lostworldbackend.exception.authentication;

import org.springframework.security.core.AuthenticationException;

public class UseridNotFoundException extends AuthenticationException {
    public UseridNotFoundException(String msg) {
        super(msg);
    }

    public UseridNotFoundException(String msg, Throwable t) {
        super(msg, t);
    }
}
