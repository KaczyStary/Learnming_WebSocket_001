package pl.piotrowskl._001.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.piotrowskl._001.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
