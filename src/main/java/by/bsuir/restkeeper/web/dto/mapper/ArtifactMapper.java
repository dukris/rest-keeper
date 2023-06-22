package by.bsuir.restkeeper.web.dto.mapper;

import by.bsuir.restkeeper.domain.Artifact;
import by.bsuir.restkeeper.web.dto.ArtifactDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.io.IOException;

@Mapper(componentModel = "spring")
public interface ArtifactMapper {

    /**
     * Map to entity.
     *
     * @param artifactDto ArtifactDto
     * @return Artifact
     */
    @Mapping(
            target = "filename",
            expression = "java(mapFilename"
                    + "(artifactDto.photo().getOriginalFilename()))"
    )
    @Mapping(
            target = "bytes",
            expression = "java(mapBytes(artifactDto.photo().getBytes()))"
    )
    Artifact toEntity(ArtifactDto artifactDto) throws IOException;

    /**
     * Filename mapping.
     *
     * @param filename Filename
     * @return Filename
     */
    default String mapFilename(String filename) {
        return filename;
    }

    /**
     * Bytes mapping.
     *
     * @param bytes Bytes
     * @return Bytes
     */
    default byte[] mapBytes(byte[] bytes) {
        return bytes;
    }

}
