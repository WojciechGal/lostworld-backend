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
import pl.lostworld.lostworldbackend.templates.LoginTemplate;
import pl.lostworld.lostworldbackend.utils.ResponseUtils;

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

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public Object createUser(@Valid @RequestBody User user) {
        //todo należy zunifikować zwracane obiekty na response entity
        return userService.saveUser(user);
    }

    //Test #1
    @GetMapping("/user")
    public User check(@AuthenticationPrincipal CurrentUser currentUser) {
        return currentUser.getActualUser();
    }

    //Test #2
    @GetMapping("/sec")
    public String sec() {
        SecurityContext context = SecurityContextHolder.getContext();
        return context.getAuthentication().getName();
    }

    @GetMapping("/checkAll")
    public List<User> checkAll() {
        List<User> allUsers = userService.findAllUsers();
        return allUsers;
    }

    //token po stronie backend'u nie jest usuwany
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginTemplate loginTemplate) {
        log.info(loginTemplate.getUsername() + " is trying to login...");

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginTemplate.getUsername(),
                        loginTemplate.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        log.info("Login successfull");

        String jwt = tokenProvider.generateToken(authentication);
        log.info("Given token: " + jwt);

        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
    }

    @GetMapping("/checkLogged")
    public ResponseEntity<?> checkLoggedUser(@AuthenticationPrincipal CurrentUser currentUser) {
        return ResponseUtils.designOkResponse(userService.findUserById(currentUser.getId()));
    }

    @GetMapping("/check/{id}")
    public ResponseEntity<?> checkUser(@PathVariable Long id) {
        return ResponseUtils.designOkResponse(userService.findUserById(id));
    }

    @PostMapping("/checkMany")
    public ResponseEntity<?> checkUsers(@RequestBody List<Long> ids) {
        return ResponseUtils.designOkResponse(userService.findAllById(ids));
    }
}
