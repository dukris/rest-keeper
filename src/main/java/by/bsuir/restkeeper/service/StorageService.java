package by.bsuir.restkeeper.service;


import by.bsuir.restkeeper.domain.Artifact;

public interface StorageService {

    /**
     * Upload photo.
     *
     * @param userId User's id
     * @param photo Artifact
     * @return Path
     */
    String uploadPhoto(Long userId, Artifact photo);

    /**
     * Delete photo.
     *
     * @param userId User's id
     * @param filename Filename
     * @return Path
     */
    String deletePhoto(Long userId, String filename);

}
