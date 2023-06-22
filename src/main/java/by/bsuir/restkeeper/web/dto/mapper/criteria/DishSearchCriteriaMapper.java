package by.bsuir.restkeeper.web.dto.mapper.criteria;

import by.bsuir.restkeeper.domain.criteria.DishSearchCriteria;
import by.bsuir.restkeeper.web.dto.criteria.DishSearchCriteriaDto;
import by.bsuir.restkeeper.web.dto.mapper.ObjectMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DishSearchCriteriaMapper
        extends ObjectMapper<DishSearchCriteria, DishSearchCriteriaDto> {

}
