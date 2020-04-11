package pl.lostworld.lostworldbackend.rating.country;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/countriesRatings")
public class CountryRatingController {

    private CountryRatingService countryRatingService;

    public CountryRatingController(CountryRatingService countryRatingService) {
        this.countryRatingService = countryRatingService;
    }

    @GetMapping("/checkAll")
    @ResponseBody
    public List<CountryRating> checkAllCountriesRatings() {
        return countryRatingService.checkAll();
    }
}
