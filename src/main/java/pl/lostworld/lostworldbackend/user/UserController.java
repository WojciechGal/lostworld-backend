package pl.lostworld.lostworldbackend.user;

import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import pl.lostworld.lostworldbackend.authentication.JwtAuthenticationResponse;
import pl.lostworld.lostworldbackend.authentication.JwtTokenProvider;
import pl.lostworld.lostworldbackend.webTemplates.LoginTemplate;

import javax.validation.Valid;
import java.util.List;

@Log
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider tokenProvider;

    public UserController(UserService userService, AuthenticationManager authenticationManager, JwtTokenProvider tokenProvider) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.tokenProvider = tokenProvider;
    }

    //prawdopodobnie obiekt będzie produkowany na froncie
//    @GetMapping("/create")
//    public User createUser() {
//        return new User();
//    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public Object createUser(@Valid @RequestBody User user) {
        return userService.saveUser(user);
    }

    @GetMapping("/check")
    public User check(@AuthenticationPrincipal CurrentUser currentUser) {
        return currentUser.getActualUser();
    }

    @GetMapping("/sec")
    public String test() {
        SecurityContext context = SecurityContextHolder.getContext();
        return context.getAuthentication().getName();
    }

    @GetMapping("/checkAll")
    public List<User> checkAll() {
        List<User> allUsers = userService.findAllUsers();
        return allUsers;
    }

    //mod
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginTemplate loginTemplate) {
        log.info(loginTemplate.getUsername());
        log.info(loginTemplate.getPassword());
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginTemplate.getUsername(),
                        loginTemplate.getPassword()
                )
        );
        log.info(authentication.toString());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateToken(authentication);
        log.info(jwt);

        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
    }

//    @PostMapping("/logout")
//    @ResponseStatus(HttpStatus.ACCEPTED)
//    public User loginUser() {
//
//    }

}
