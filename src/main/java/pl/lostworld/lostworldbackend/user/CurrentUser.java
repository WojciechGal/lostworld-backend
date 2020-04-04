package pl.lostworld.lostworldbackend.user;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class CurrentUser extends org.springframework.security.core.userdetails.User {
    private final User user;

    //mod
    private final Long id;

    public CurrentUser(String username, String password,
                       Collection<? extends GrantedAuthority> authorities,
                       User user) {
        super(username, password, authorities);
        this.user = user;
        //mod
        this.id = user.getId();
    }

    public User getUser() {
        return user;
    }

    //mod
    public Long getId() {
        return id;
    }
}
