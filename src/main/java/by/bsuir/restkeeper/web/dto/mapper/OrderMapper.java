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

    /**
     * Map to entity.
     *
     * @param dto OrderDto
     * @return Order
     */
    @Mapping(
            target = "dishAmountMap",
            expression = "java(mapToEntity())"
    )
    Order toEntity(OrderDto dto);

    /**
     * Map to dto.
     *
     * @param entity Order
     * @return OrderDto
     */
    @Mapping(
            target = "dishAmountMap",
            expression = "java(mapToDto(entity.getDishAmountMap()))"
    )
    OrderDto toDto(Order entity);

    /**
     * Map to dto.
     *
     * @param entities List of orders
     * @return List of dto
     */
    @Mapping(
            target = "dishAmountMap",
            expression = "java(mapToDto(entity.getDishAmountMap()))"
    )
    List<OrderDto> toDto(List<Order> entities);

    /**
     * Dishes mapping to dto.
     *
     * @param dishAmountMap Map of dishes
     * @return Map of names of dishes
     */
    default Map<String, Integer> mapToDto(Map<Dish, Integer> dishAmountMap) {
        Map<String, Integer> returnedMap = new HashMap<>();
        dishAmountMap.forEach((key, value) ->
                returnedMap.put(key.getName(), value)
        );
        return returnedMap;
    }

    /**
     * Dishes mapping to entity.
     *
     * @return Map of dishes
     */
    default Map<Dish, Integer> mapToEntity() {
        return new HashMap<>();
    }

}
