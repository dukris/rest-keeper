package by.bsuir.restkeeper.persistence;

import by.bsuir.restkeeper.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Find users by role.
     *
     * @param role Role
     * @return List of users
     */
    List<User> findByRole(User.Role role);

    /**
     * Find users by surname.
     *
     * @param surname Surname
     * @return List of users
     */
    List<User> findBySurname(String surname);

    /**
     * Find user by email.
     *
     * @param email Email
     * @return User
     */
    Optional<User> findByEmail(String email);

    /**
     * Is exists by email.
     *
     * @param email Email
     * @return Boolean
     */
    Boolean existsByEmail(String email);

}
