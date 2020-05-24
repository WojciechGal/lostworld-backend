package pl.lostworld.lostworldbackend.user.additionalResources.plan;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.lostworld.lostworldbackend.user.User;

import java.util.List;

@Service
@Transactional
public class TravelPlanService {

    private TravelPlanRepository travelPlanRepository;

    public TravelPlanService(TravelPlanRepository travelPlanRepository) {
        this.travelPlanRepository = travelPlanRepository;
    }

    public List<TravelPlan> checkAll() {
        return travelPlanRepository.findAll();
    }

    public TravelPlan save(TravelPlan travelPlan) {
        return travelPlanRepository.save(travelPlan);
    }

    public TravelPlan checkById(Long id) {
        return travelPlanRepository.findById(id).orElse(null);
    }

    public TravelPlan createNewTravelPlanForUser(User user) {
        TravelPlan travelPlan = new TravelPlan();
        travelPlan.setUser(user);
        return travelPlan;
    }
}
