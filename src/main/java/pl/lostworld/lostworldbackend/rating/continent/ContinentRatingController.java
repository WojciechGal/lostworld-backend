package pl.lostworld.lostworldbackend.rating.continent;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ratings/continents")
public class ContinentRatingController {

    private ContinentRatingService continentRatingService;

    public ContinentRatingController(ContinentRatingService continentRatingService) {
        this.continentRatingService = continentRatingService;
    }

    @GetMapping("/checkAll")
    @ResponseBody
    public List<ContinentRating> checkAllContinentsRatings() {
        return continentRatingService.checkAll();
    }
}
