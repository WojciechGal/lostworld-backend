package pl.lostworld.lostworldbackend.user.additionalResources.plan;

import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import pl.lostworld.lostworldbackend.user.CurrentUser;

import javax.validation.Valid;
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
    public TravelPlan addTravelPlan(@AuthenticationPrincipal CurrentUser currentUser) {
        return travelPlanService.createNewTravelPlanForUser(currentUser.getActualUser());
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public TravelPlan addTravelPlan(@Valid @RequestBody TravelPlan travelPlan) {
        return travelPlanService.save(travelPlan);
    }
}
