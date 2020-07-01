package pl.lostworld.lostworldbackend.user;

import java.util.List;

public interface UserService {

    User findUserByUsername(String username);

    User saveUser(User user);

    public User findUserById(Long id);

    public void deleteUserById(Long id);

    public List<User> findAllUsers();

    public List<User> findAllById(List<Long> ids);
}