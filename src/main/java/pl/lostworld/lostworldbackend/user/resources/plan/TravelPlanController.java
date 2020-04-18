package pl.lostworld.lostworldbackend.user.resources.plan;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/travelPlans")
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
