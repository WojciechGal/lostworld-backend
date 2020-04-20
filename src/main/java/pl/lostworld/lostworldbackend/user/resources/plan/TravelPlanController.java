package pl.lostworld.lostworldbackend.user.resources.plan;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.lostworld.lostworldbackend.continent.Continent;
import pl.lostworld.lostworldbackend.country.Country;
import pl.lostworld.lostworldbackend.country.CountryService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/travelPlans")
@Log
public class TravelPlanController {

    private TravelPlanService travelPlanService;

    public TravelPlanController(TravelPlanService travelPlanService) {
        this.travelPlanService = travelPlanService;
    }

    @GetMapping("/checkAll")
    public List<TravelPlan> checkAllTravelPlans() {
        return travelPlanService.checkAll();
    }

    @GetMapping("/add")
    public TravelPlan addTravelPlan() {
        return new TravelPlan();
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public TravelPlan addTravelPlan(@Valid @RequestBody TravelPlan travelPlan) {
        return travelPlanService.add(travelPlan);
    }
}
