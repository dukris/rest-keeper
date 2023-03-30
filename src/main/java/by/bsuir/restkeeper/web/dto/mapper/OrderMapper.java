package by.bsuir.restkeeper.web.dto.mapper;

import by.bsuir.restkeeper.domain.Order;
import by.bsuir.restkeeper.web.dto.OrderDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = DishMapper.class)
public interface OrderMapper {

    Order toEntity(OrderDto dto);

    OrderDto toDto(Order entity);

    List<OrderDto> toDto(List<Order> entities);

}
