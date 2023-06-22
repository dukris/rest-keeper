package by.bsuir.restkeeper.web.dto.mapper;

import by.bsuir.restkeeper.domain.Dish;
import by.bsuir.restkeeper.web.dto.DishDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DishMapper
        extends ObjectMapper<Dish, DishDto> {

}
