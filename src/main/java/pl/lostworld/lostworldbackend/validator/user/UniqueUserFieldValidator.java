package pl.lostworld.lostworldbackend.validator.user;

import org.springframework.beans.factory.annotation.Autowired;
import pl.lostworld.lostworldbackend.user.User;
import pl.lostworld.lostworldbackend.user.UserService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueUserFieldValidator implements ConstraintValidator<UniqueUserField, String> {

    @Autowired
    private UserService userService;

    private String column;

    @Override
    public void initialize(UniqueUserField constraintAnnotation) {
        this.column = constraintAnnotation.column();
    }
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if ("username".equals(column)) {
            return userService.findAllUsers().stream().map(User::getUsername).noneMatch(p -> p.equals(value));
        }
        if ("email".equals(column)) {
            return userService.findAllUsers().stream().map(User::getEmail).noneMatch(p -> p.equals(value));
        }
        return true;
    }
}