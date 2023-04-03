package by.bsuir.restkeeper.service;


import by.bsuir.restkeeper.domain.Artifact;

public interface StorageService {

    String uploadPhoto(Long userId, Artifact photo);

    String deletePhoto(Long userId, String filename);

}
