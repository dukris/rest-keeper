package by.bsuir.restkeeper.web.dto.mapper.criteria;

import by.bsuir.restkeeper.domain.criteria.DishSearchCriteria;
import by.bsuir.restkeeper.web.dto.criteria.DishSearchCriteriaDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DishSearchCriteriaMapper {

    DishSearchCriteria toEntity(DishSearchCriteriaDto dto);

}
