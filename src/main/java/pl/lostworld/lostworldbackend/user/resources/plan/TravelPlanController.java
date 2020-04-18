package pl.lostworld.lostworldbackend.user.resources.plan;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.lostworld.lostworldbackend.continent.Continent;
import pl.lostworld.lostworldbackend.continent.ContinentService;
import pl.lostworld.lostworldbackend.user.resources.plan.sequence.ContinentInSequence;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/travelPlans")
public class TravelPlanController {

    private TravelPlanService travelPlanService;

    private ContinentService continentService;

    public TravelPlanController(TravelPlanService travelPlanService, ContinentService continentService) {
        this.travelPlanService = travelPlanService;
        this.continentService = continentService;
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

    @GetMapping("/test")
    public String test() {

        TravelPlan trav1 = new TravelPlan();

        ContinentInSequence cont1 = new ContinentInSequence();
        //cont1.setTravelPlan(trav1);
        cont1.setContinent(continentService.checkById(1L));
        cont1.setPositionInPlan(1);

        ContinentInSequence cont2 = new ContinentInSequence();
        //cont2.setTravelPlan(trav1);
        cont2.setContinent(continentService.checkById(2L));
        cont2.setPositionInPlan(2);

        List<ContinentInSequence> sequence = new ArrayList<>();

        trav1.setSequenceOfContinents(sequence);

        travelPlanService.add(trav1);

        ////////////

        TravelPlan trav2 = new TravelPlan();

        ContinentInSequence cont3 = new ContinentInSequence();
        //cont1.setTravelPlan(trav1);
        cont3.setContinent(continentService.checkById(2L));
        cont3.setPositionInPlan(1);

        ContinentInSequence cont4 = new ContinentInSequence();
        //cont2.setTravelPlan(trav1);
        cont4.setContinent(continentService.checkById(1L));
        cont4.setPositionInPlan(2);

        List<ContinentInSequence> sequence2 = new ArrayList<>();

        trav2.setSequenceOfContinents(sequence2);

        travelPlanService.add(trav2);

        return "ok";
    }
}
