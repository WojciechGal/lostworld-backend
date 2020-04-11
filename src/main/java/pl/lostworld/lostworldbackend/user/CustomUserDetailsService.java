package pl.lostworld.lostworldbackend.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import pl.lostworld.lostworldbackend.exception.authentication.UseridNotFoundException;

public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userService.findUserByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException(username);
        }

        return CurrentUser.create(user);
    }

    public UserDetails loadUserById(Long id) {
        User user = userService.findUserById(id);

        if (user == null) {
            throw new UseridNotFoundException(id.toString());
        }

        return CurrentUser.create(user);
    }
}
