package by.bsuir.restkeeper.web.dto.mapper;

import by.bsuir.restkeeper.domain.User;
import by.bsuir.restkeeper.web.dto.UserDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = AddressMapper.class)
public interface UserMapper
        extends ObjectMapper<User, UserDto> {

}
