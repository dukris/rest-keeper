package by.bsuir.restkeeper.service.impl;

import by.bsuir.restkeeper.service.FileService;
import lombok.SneakyThrows;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
public class FileServiceImpl implements FileService {

    @Override
    @SneakyThrows
    public ByteArrayResource download(final String filename) {
        byte[] bytes = Files.readAllBytes(Paths.get(filename));
        return new ByteArrayResource(bytes);
    }

    @Override
    public void delete(final String filename) {
        new File(filename).delete();
    }

}
