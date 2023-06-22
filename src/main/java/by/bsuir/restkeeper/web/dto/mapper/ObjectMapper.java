package by.bsuir.restkeeper.web.dto.mapper;

import java.util.List;

public interface ObjectMapper<Entity, Dto> {

    /**
     * Map to entity.
     *
     * @param dto Dto
     * @return Entity
     */
    Entity toEntity(Dto dto);

    /**
     * Map to dto.
     *
     * @param entity Entity
     * @return Dto
     */
    Dto toDto(Entity entity);

    /**
     * Map to dto.
     *
     * @param entities List of entities
     * @return Dto
     */
    List<Dto> toDto(List<Entity> entities);

}
