package pl.lostworld.lostworldbackend.user;

public interface UserService {

    User findByUsername(String username);

    void saveUser(User user);
}