package by.bsuir.restkeeper.web.dto.mapper;

import by.bsuir.restkeeper.domain.Address;
import by.bsuir.restkeeper.web.dto.AddressDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AddressMapper extends ObjectMapper<Address, AddressDto> {

}
