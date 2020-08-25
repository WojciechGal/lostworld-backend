package pl.lostworld.lostworldbackend.utils;

import org.hibernate.Hibernate;
import pl.lostworld.lostworldbackend.templates.UserResource;

import java.util.Optional;

public class HibernateUtils {

    public static void initializeSequenceOfTerritoryEntities(Optional<? extends UserResource> userResource) {
        Hibernate.initialize(userResource.get().getSequenceOfContinents());
        Hibernate.initialize(userResource.get().getSequenceOfCountries());
        Hibernate.initialize(userResource.get().getSequenceOfCities());
        Hibernate.initialize(userResource.get().getSequenceOfRelics());
    }
}
