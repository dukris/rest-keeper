package by.bsuir.restkeeper.web.dto.mapper;

import by.bsuir.restkeeper.domain.Order;
import by.bsuir.restkeeper.web.dto.CreateOrderDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CreateOrderMapper
        extends ObjectMapper<Order, CreateOrderDto> {

}
