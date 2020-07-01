package pl.lostworld.lostworldbackend.user;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.lostworld.lostworldbackend.role.Role;
import pl.lostworld.lostworldbackend.role.RoleRepository;

import java.util.*;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository,
                           BCryptPasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    /*
    inicjalizacja hibernate'a  umożliwiała wykorzystanie obiektu user w obiekcie current user z authentication
    principal ze wzgledu na koniecznosc dodatkowych inicjalizacji lub odgórnego fetchowanie w trybie EAGER - w
    kontrolerze następuje wczytanie obiektu usera z id currentuser'a
    todo jest to miejsce ewentualnej optymalizacji
     */

    @Override
    public User findUserByUsername(String username) {
//        User user =  userRepository.findByUsername(username);
//        Hibernate.initialize(user.getContinentRatingList());
//        Hibernate.initialize(user.getCountryRatingList());
        return userRepository.findByUsername(username);
    }

    @Override
    public User saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEnabled(1);
        Role userRole = roleRepository.findByName("ROLE_USER");
        user.setRoles(new HashSet<Role>(Collections.singletonList(userRole)));
        return userRepository.save(user);
    }

    @Override
    public User findUserById(Long id) {
//        User user =  userRepository.findById(id).orElse(null);
//        Hibernate.initialize(user.getContinentRatingList());
//        Hibernate.initialize(user.getCountryRatingList());
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public List<User> findAllById(List<Long> ids) {
        return userRepository.findAllById(ids);
    }
}
