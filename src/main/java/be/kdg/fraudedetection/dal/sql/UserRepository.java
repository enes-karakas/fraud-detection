package be.kdg.fraudedetection.dal.sql;

import be.kdg.fraudedetection.bl.dom.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findUserByUsername(String username);
}
