package pl.lostworld.lostworldbackend.utils;

import pl.lostworld.lostworldbackend.templates.Pair;

import javax.validation.ConstraintViolation;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ValidationUtils {

    public static <T> List<Pair> mapViolationsForResponse(Set<ConstraintViolation<T>> violations) {
        return violations
                .stream()
                .map(violation -> new Pair<>(violation.getPropertyPath().toString(), violation.getMessage()))
                .collect(Collectors.toList());
    }
}
