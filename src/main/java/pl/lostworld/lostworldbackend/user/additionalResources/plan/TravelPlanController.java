package pl.lostworld.lostworldbackend.user.additionalResources.plan;

import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import pl.lostworld.lostworldbackend.user.CurrentUser;
import pl.lostworld.lostworldbackend.user.additionalResources.article.Article;
import pl.lostworld.lostworldbackend.utils.ResponseUtils;
import pl.lostworld.lostworldbackend.utils.ValidationUtils;

import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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

    @GetMapping("/checkAllForLoggedUser")
    public ResponseEntity<?> checkAllTravelPlansForLoggedUser(@AuthenticationPrincipal CurrentUser currentUser) {
        return ResponseUtils.designOkResponse(travelPlanService.checkAllByUserId(currentUser.getId()));
    }

    @GetMapping("/add")
    public ResponseEntity<?> addTravelPlan() {
        return ResponseUtils.designOkResponse(new TravelPlan());
    }

    @PostMapping("/addForLoggedUser")
    public ResponseEntity<?> addTravelPlanForLoggedUser(@RequestBody TravelPlan travelPlan, @AuthenticationPrincipal CurrentUser currentUser) {
        Set<ConstraintViolation<TravelPlan>> violations = travelPlanService.setUserAndValidate(travelPlan, currentUser.getActualUser());
        if (violations.isEmpty()) {
            return ResponseUtils.designCreatedResponse(travelPlanService.setUserAndSave(travelPlan, currentUser.getActualUser()));
        } else {
            return ResponseUtils.designBadRequestResponse(ValidationUtils.mapViolationsForResponse(violations));
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateTravelPlan (@Valid @RequestBody TravelPlan travelPlan) {
        return ResponseUtils.designOkResponse(travelPlanService.save(travelPlan));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteTravelPlan(@PathVariable Long id) {
        Optional<TravelPlan> travelPlan = travelPlanService.deleteById(id);
        if (travelPlan.isPresent()) {
            return ResponseUtils.designOkResponse(travelPlan.get());
        } else {
            return ResponseUtils.designBadRequestSingletonResponse("No such element");
        }
    }
}
