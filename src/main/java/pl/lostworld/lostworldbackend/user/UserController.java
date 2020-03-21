package pl.lostworld.lostworldbackend.user;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/create")
    public String createUser(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "test/register";
    }

    @PostMapping("/create")
    public String createUser(@Valid User user, BindingResult result, Model model) {

        if (result.hasErrors()) {
            model.addAttribute("user", user);
            return "test/register";
        }
        userService.saveUser(user);
        return "redirect:/";
    }

    @GetMapping("/check")
    @ResponseBody
    public String check(@AuthenticationPrincipal CurrentUser customUser) {
        User entityUser = customUser.getUser();
        return "You are " + entityUser.getId() + ", " + entityUser.getUsername() + ", " + entityUser.getEmail() + ", "
                + entityUser.getPassword() + ", " + entityUser.getEnabled()
                + ", and you are authenticated as " + entityUser.getRoles();
    }

}
