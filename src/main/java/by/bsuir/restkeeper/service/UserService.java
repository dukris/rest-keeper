package by.bsuir.restkeeper.service;

import by.bsuir.restkeeper.domain.Artifact;
import by.bsuir.restkeeper.domain.User;
import by.bsuir.restkeeper.domain.criteria.UserSearchCriteria;

import java.util.List;

public interface UserService {

    List<User> retrieveAllByCriteria(UserSearchCriteria criteria);

    User retrieveById(Long id);

    User retrieveByEmail(String email);

    User updatePassword(User user, String newPassword);

    User create(User user);

    User update(User user);

    void delete(Long id);

    User addPhoto(Long id, Artifact photo);

    void deletePhoto(Long id, String filename);

}
