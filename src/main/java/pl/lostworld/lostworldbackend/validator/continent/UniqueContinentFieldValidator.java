package pl.lostworld.lostworldbackend.validator.continent;

import org.springframework.beans.factory.annotation.Autowired;
import pl.lostworld.lostworldbackend.continent.Continent;
import pl.lostworld.lostworldbackend.continent.ContinentService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueContinentFieldValidator implements ConstraintValidator<UniqueContinentField, String> {

    @Autowired
    private ContinentService continentService;

    private String column;

    @Override
    public void initialize(UniqueContinentField constraintAnnotation) {
        this.column = constraintAnnotation.column();
    }
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if ("name".equals(column)) {
            return continentService.checkAll().stream().map(Continent::getName).noneMatch(p -> p.equals(value));
        }
        return true;
    }
}