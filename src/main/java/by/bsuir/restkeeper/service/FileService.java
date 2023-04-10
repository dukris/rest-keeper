package by.bsuir.restkeeper.service;

import org.springframework.core.io.ByteArrayResource;

public interface FileService {

    ByteArrayResource download(String filename);

    void delete(String filename);

}
