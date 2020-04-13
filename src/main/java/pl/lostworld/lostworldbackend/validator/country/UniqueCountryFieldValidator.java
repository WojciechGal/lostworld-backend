package pl.lostworld.lostworldbackend.validator.country;

import org.springframework.beans.factory.annotation.Autowired;
import pl.lostworld.lostworldbackend.country.Country;
import pl.lostworld.lostworldbackend.country.CountryService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueCountryFieldValidator implements ConstraintValidator<UniqueCountryField, String> {

    @Autowired
    private CountryService countryService;

    private String column;

    @Override
    public void initialize(UniqueCountryField constraintAnnotation) {
        this.column = constraintAnnotation.column();
    }
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if ("name".equals(column)) {
            return countryService.checkAll().stream().map(Country::getName).noneMatch(p -> p.equals(value));
        }
        return true;
    }
}