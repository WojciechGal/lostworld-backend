package pl.lostworld.lostworldbackend.user.additionalResources.plan;

import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.lostworld.lostworldbackend.user.User;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class TravelPlanService {

    private TravelPlanRepository travelPlanRepository;

    private Validator validator;

    public TravelPlanService(TravelPlanRepository travelPlanRepository, Validator validator) {
        this.travelPlanRepository = travelPlanRepository;
        this.validator = validator;
    }

    public List<TravelPlan> checkAll() {
        return travelPlanRepository.findAll();
    }

    public Optional<TravelPlan> checkById(Long id) {
        return travelPlanRepository.findById(id);
    }

    public List<TravelPlan> checkAllByUserId(Long id) {
        return travelPlanRepository.findAllByUserId(id);
    }

    public TravelPlan save(TravelPlan travelPlan) {
        return travelPlanRepository.save(travelPlan);
    }

    public TravelPlan setUserAndSave(TravelPlan travelPlan, User user) {
        travelPlan.setUser(user);
        return travelPlanRepository.save(travelPlan);
    }

    public Set<ConstraintViolation<TravelPlan>> setUserAndValidate(TravelPlan travelPlan, User user) {
        travelPlan.setUser(user);
        return validator.validate(travelPlan);
    }

    public Optional<TravelPlan> deleteById(Long id) {
        Optional<TravelPlan> travelPlan = travelPlanRepository.findById(id);
        if (travelPlan.isPresent()) {
            //dociągnięcie danych jest wymagane przed usunięciem obiektu z DB
            Hibernate.initialize(travelPlan.get().getSequenceOfContinents());
            Hibernate.initialize(travelPlan.get().getSequenceOfCountries());
            Hibernate.initialize(travelPlan.get().getSequenceOfCities());
            Hibernate.initialize(travelPlan.get().getSequenceOfRelics());
            travelPlanRepository.deleteById(id);
            return travelPlan;
        } else {
            return travelPlan;
        }
    }
}
