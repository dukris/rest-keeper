package by.bsuir.restkeeper.web.dto.mapper;

import by.bsuir.restkeeper.domain.User;
import by.bsuir.restkeeper.web.dto.UserDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = AddressMapper.class)
public interface UserMapper {

    User toEntity(UserDto dto);

    UserDto toDto(User entity);

    List<UserDto> toDto(List<User> entities);

}
