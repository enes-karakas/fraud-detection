package be.kdg.fraudedetection.bl.service;

import be.kdg.fraudedetection.bl.dom.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User findUserById(int id) throws RuntimeException;

    User saveUser(User user) throws RuntimeException;

    User addUser(User user) throws RuntimeException;

    void checkLogin(Integer userId, String password) throws RuntimeException;

}
