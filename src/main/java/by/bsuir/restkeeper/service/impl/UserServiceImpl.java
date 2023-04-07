package by.bsuir.restkeeper.service.impl;

import by.bsuir.restkeeper.domain.Address;
import by.bsuir.restkeeper.domain.Artifact;
import by.bsuir.restkeeper.domain.User;
import by.bsuir.restkeeper.domain.criteria.UserSearchCriteria;
import by.bsuir.restkeeper.domain.exception.ResourceAlreadyExistsException;
import by.bsuir.restkeeper.domain.exception.ResourceNotFoundException;
import by.bsuir.restkeeper.persistence.UserRepository;
import by.bsuir.restkeeper.service.StorageService;
import by.bsuir.restkeeper.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final StorageService storageService;

    @Override
    public List<User> retrieveAllByCriteria(UserSearchCriteria criteria) {
        return criteria.getRole() != null ?
                userRepository.findByRole(criteria.getRole()) :
                userRepository.findAll();
    }

    @Override
    public User retrieveById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User with id = " + id + " not found!"));
    }

    @Override
    public User retrieveByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User with email = " + email + " not found!"));
    }

    @Override
    public User updatePassword(User user, String newPassword) {
        user.setPassword(newPassword);
        return userRepository.save(user);
    }

    @Override
    public User create(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new ResourceAlreadyExistsException("User with email = " + user.getEmail() + " already exists!");
        }
        return userRepository.save(user);
    }

    @Override
    public User update(User user) {
        User foundUser = retrieveById(user.getId());
        foundUser.setName(user.getName());
        foundUser.setSurname(user.getSurname());
        foundUser.setRole(user.getRole());
        foundUser.setPassport(user.getPassport());
        foundUser.setDateOfBirth(user.getDateOfBirth());
        foundUser.setPhoneNumber(user.getPhoneNumber());
        Address address = new Address();
        address.setCity(user.getAddress().getCity());
        address.setStreet(user.getAddress().getStreet());
        address.setHouse(user.getAddress().getHouse());
        address.setFlat(user.getAddress().getFlat());
        foundUser.setAddress(address);
        return userRepository.save(foundUser);
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User addPhoto(Long id, Artifact photo) {
        User user = retrieveById(id);
        String path = storageService.uploadPhoto(id, photo);
        user.setPhotoPath(path);
        return userRepository.save(user);
    }

    @Override
    public void deletePhoto(Long id, String filename) {
        User user = retrieveById(id);
        String path = storageService.deletePhoto(id, filename);
        user.setPhotoPath(path);
        userRepository.save(user);
    }

}
