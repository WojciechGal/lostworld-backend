package pl.lostworld.lostworldbackend.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import pl.lostworld.lostworldbackend.authentication.UserPrincipal;
import pl.lostworld.lostworldbackend.exception.authentication.UseridNotFoundException;

import java.util.HashSet;
import java.util.Set;

public class SpringDataUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userService.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException(username);
        }

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        user.getRoles().forEach(r ->
                grantedAuthorities.add(new SimpleGrantedAuthority(r.getName())));

        return new CurrentUser(user.getUsername(),user.getPassword(),
                grantedAuthorities, user);
    }

    //mod
    public UserDetails loadUserById(Long id) {
        User user = userService.findUserById(id);

        if (user == null) {
            throw new UseridNotFoundException(id.toString());
        }

        return UserPrincipal.create(user);
    }
}
