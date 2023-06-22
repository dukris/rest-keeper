package by.bsuir.restkeeper.service;

import org.springframework.core.io.ByteArrayResource;

public interface FileService {

    /**
     * Download file.
     *
     * @param filename Filename
     * @return File
     */
    ByteArrayResource download(String filename);

    /**
     * Delete file.
     *
     * @param filename Filename
     */
    void delete(String filename);

}
