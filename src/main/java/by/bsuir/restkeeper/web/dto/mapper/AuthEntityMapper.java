package by.bsuir.restkeeper.web.dto.mapper;

import by.bsuir.restkeeper.domain.AuthEntity;
import by.bsuir.restkeeper.web.dto.AuthEntityDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthEntityMapper
        extends ObjectMapper<AuthEntity, AuthEntityDto> {

}
