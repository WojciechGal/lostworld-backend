package pl.lostworld.lostworldbackend.validator;

import org.aspectj.weaver.ast.Test;
import org.springframework.beans.factory.annotation.Autowired;
import pl.lostworld.lostworldbackend.city.City;
import pl.lostworld.lostworldbackend.city.CityService;
import pl.lostworld.lostworldbackend.continent.Continent;
import pl.lostworld.lostworldbackend.continent.ContinentService;
import pl.lostworld.lostworldbackend.country.Country;
import pl.lostworld.lostworldbackend.country.CountryService;
import pl.lostworld.lostworldbackend.relic.Relic;
import pl.lostworld.lostworldbackend.relic.RelicService;
import pl.lostworld.lostworldbackend.user.additionalResources.album.Album;
import pl.lostworld.lostworldbackend.user.additionalResources.album.AlbumService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public class AllExistsValidator implements ConstraintValidator<AllExists, Object> {

    @Autowired
    private ContinentService continentService;

    @Autowired
    private CountryService countryService;

    @Autowired
    private CityService cityService;

    @Autowired
    private RelicService relicService;

    @Autowired
    private AlbumService albumService;

    @Override
    public void initialize(AllExists constraintAnnotation) {
    }
    @Override
    public boolean isValid(Object listToValidate, ConstraintValidatorContext context) {
        if (((List) listToValidate).size() > 0) {
            if (((List) listToValidate).get(0) instanceof Continent) {
                return ((List<?>)listToValidate).stream().allMatch(objectToValidate -> continentService.checkIfExists(((Continent) objectToValidate).getId()));
            } else if (((List) listToValidate).get(0) instanceof Country) {
                return ((List<?>)listToValidate).stream().allMatch(objectToValidate -> countryService.checkIfExists(((Country) objectToValidate).getId()));
            } else if (((List) listToValidate).get(0) instanceof City) {
                return ((List<?>)listToValidate).stream().allMatch(objectToValidate -> cityService.checkIfExists(((City) objectToValidate).getId()));
            } else if (((List) listToValidate).get(0) instanceof Relic) {
                return ((List<?>)listToValidate).stream().allMatch(objectToValidate -> relicService.checkIfExists(((Relic) objectToValidate).getId()));
            } else if (((List) listToValidate).get(0) instanceof Album) {
                return ((List<?>)listToValidate).stream().allMatch(objectToValidate -> albumService.checkIfExists(((Album) objectToValidate).getId()));
            }
        }
        return true;
    }
}