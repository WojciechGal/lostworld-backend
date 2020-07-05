package pl.lostworld.lostworldbackend.role;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class RoleInitialization {

    private final RoleRepository roleRepository;

    public RoleInitialization(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @EventListener
    public void appReady(ApplicationReadyEvent event) {
        Arrays.asList(new Role(1, "ROLE_USER"),
                new Role(2, "ROLE_ADMIN"),
                new Role(3, "ROLE_MODERATOR"))
                .forEach(roleRepository::save);
    }
}
