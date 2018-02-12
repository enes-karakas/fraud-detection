package be.kdg.fraudedetection.bl.service;

import be.kdg.fraudedetection.bl.dom.User;
import be.kdg.fraudedetection.dal.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User u = repository.findUserByUsername(username);

        if (u == null) throw new RuntimeException("No such user");

        return u;
    }

    @Override
    public User findUserById(int id) throws RuntimeException {
        User u = repository.findOne(id);

        if (u == null)
            throw new RuntimeException("User not found");

        return u;
    }

    @Override
    public User saveUser(User user) throws RuntimeException {
        User u = repository.save(user);
        if (u == null)
            throw new RuntimeException("User not saved");
        return u;
    }

    @Override
    public User addUser(User user) throws RuntimeException {
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return this.saveUser(user);
    }

    @Override
    public void checkLogin(Integer userId, String password) throws RuntimeException {
        User u = repository.findOne(userId);

        if (u == null || !passwordEncoder.matches(password, u.getPassword())) {
            throw new RuntimeException("Username or password wrong for user with id: " + u.getUserId());
        }
    }


}
