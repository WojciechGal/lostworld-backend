package pl.lostworld.lostworldbackend.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import pl.lostworld.lostworldbackend.jsonTemplates.ResponseValidationFailedTemplate;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    //prawdopodobnie obiekt będzie produkowany na froncie
//    @GetMapping("/create")
//    public User createUser() {
//        return new User();
//    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public Object createUser(@Valid @RequestBody User user) {

        //dodatkowa walidacja zapobiegająca 500 - persistence error
        ResponseValidationFailedTemplate responseValidationFailedTemplate = new ResponseValidationFailedTemplate(400);

        if (userService.findUsers().stream().map(User::getUsername).anyMatch(p -> p.equals(user.getUsername()))) {
            responseValidationFailedTemplate.addError("username", "username already exists");
        }

        if (userService.findUsers().stream().map(User::getEmail).anyMatch(p -> p.equals(user.getEmail()))) {
            responseValidationFailedTemplate.addError("email", "email already exists");
        }

        if (!responseValidationFailedTemplate.getErrors().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseValidationFailedTemplate);
        }

        return userService.saveUser(user);
    }

    @GetMapping("/check")
    public User check(@AuthenticationPrincipal CurrentUser customUser) {
        return customUser.getUser();
    }

    @GetMapping("/sec")
    public String test() {
        SecurityContext context = SecurityContextHolder.getContext();
        return context.getAuthentication().getName();
    }

    @GetMapping("/checkall")
    public List<User> checkall() {
        List<User> allUsers = userService.findUsers();
        return allUsers;
    }

//    @PostMapping("/login")
//    @ResponseStatus(HttpStatus.ACCEPTED)
//    public User loginUser() {
//
//    }

//    @PostMapping("/logout")
//    @ResponseStatus(HttpStatus.ACCEPTED)
//    public User loginUser() {
//
//    }

}
