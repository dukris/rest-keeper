package by.bsuir.restkeeper.service;

import by.bsuir.restkeeper.domain.Artifact;
import by.bsuir.restkeeper.domain.User;
import by.bsuir.restkeeper.domain.criteria.UserSearchCriteria;

import java.util.List;

public interface UserService {

    /**
     * Retrieve all users.
     *
     * @param criteria Criteria
     * @return List of users
     */
    List<User> retrieveAllByCriteria(UserSearchCriteria criteria);

    /**
     * Retrieve user by id.
     *
     * @param id Id
     * @return User
     */
    User retrieveById(Long id);

    /**
     * Retrieve user by email.
     *
     * @param email Email
     * @return User
     */
    User retrieveByEmail(String email);

    /**
     * Update password.
     *
     * @param user User
     * @param newPassword New password.
     * @return User
     */
    User updatePassword(User user, String newPassword);

    /**
     * Create new user.
     *
     * @param user User
     * @return User
     */
    User create(User user);

    /**
     * Update user.
     *
     * @param user User
     * @return User
     */
    User update(User user);

    /**
     * Enable user.
     *
     * @param email Email
     * @return User
     */
    User enable(String email);

    /**
     * Delete user by id.
     *
     * @param id Id
     */
    void delete(Long id);

    /**
     * Add photo.
     *
     * @param id Id
     * @param photo Artifact
     * @return User
     */
    User addPhoto(Long id, Artifact photo);

    /**
     * Delete photo.
     *
     * @param id Id
     * @param filename Filename
     */
    void deletePhoto(Long id, String filename);

}
