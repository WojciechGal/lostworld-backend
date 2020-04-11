package pl.lostworld.lostworldbackend.rating;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.lostworld.lostworldbackend.rating.continent.ContinentRating;
import pl.lostworld.lostworldbackend.rating.continent.ContinentRatingService;
import pl.lostworld.lostworldbackend.rating.country.CountryRating;
import pl.lostworld.lostworldbackend.rating.country.CountryRatingService;

import java.util.List;

@RestController
@RequestMapping("/ratings")
public class RatingController {

    private ContinentRatingService continentRatingService;
    private CountryRatingService countryRatingService;

    public RatingController(ContinentRatingService continentRatingService, CountryRatingService countryRatingService) {
        this.continentRatingService = continentRatingService;
        this.countryRatingService = countryRatingService;
    }

    @GetMapping("/continents/checkAll")
    public List<ContinentRating> checkAllContinentsRatings() {
        return continentRatingService.checkAll();
    }

    @GetMapping("/countries/checkAll")
    public List<CountryRating> checkAllCountriesRatings() {
        return countryRatingService.checkAll();
    }
}
