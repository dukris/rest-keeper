package by.bsuir.restkeeper.persistence;

import by.bsuir.restkeeper.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByRole(User.Role role);

    Optional<User> findByEmail(String email);

    Boolean existsByEmail(String email);

}
