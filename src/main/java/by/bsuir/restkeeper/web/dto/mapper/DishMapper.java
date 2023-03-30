package by.bsuir.restkeeper.web.dto.mapper;

import by.bsuir.restkeeper.domain.Dish;
import by.bsuir.restkeeper.web.dto.DishDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DishMapper {

    Dish toEntity(DishDto dto);

    DishDto toDto(Dish entity);

    List<DishDto> toDto(List<Dish> entities);

}
