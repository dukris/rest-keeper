package by.bsuir.restkeeper.web.dto.mapper;

import by.bsuir.restkeeper.domain.Dish;
import by.bsuir.restkeeper.domain.Order;
import by.bsuir.restkeeper.web.dto.OrderDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    @Mapping(target = "dishAmountMap", expression = "java(mapToEntity())")
    Order toEntity(OrderDto dto);

    @Mapping(target = "dishAmountMap", expression = "java(mapToDto(entity.getDishAmountMap()))")
    OrderDto toDto(Order entity);

    @Mapping(target = "dishAmountMap", expression = "java(mapToDto(entity.getDishAmountMap()))")
    List<OrderDto> toDto(List<Order> entities);

    default Map<String, Integer> mapToDto(Map<Dish, Integer> dishAmountMap) {
        Map<String, Integer> returnedMap = new HashMap<>();
        dishAmountMap.forEach((key, value) -> {
            returnedMap.put(key.getName(), value);
        });
        return returnedMap;
    }

    default Map<Dish, Integer> mapToEntity() {
        return new HashMap<>();
    }

}
