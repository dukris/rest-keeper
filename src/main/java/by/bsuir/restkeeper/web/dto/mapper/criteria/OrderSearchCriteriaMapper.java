package by.bsuir.restkeeper.web.dto.mapper.criteria;

import by.bsuir.restkeeper.domain.criteria.OrderSearchCriteria;
import by.bsuir.restkeeper.web.dto.criteria.OrderSearchCriteriaDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderSearchCriteriaMapper {

    OrderSearchCriteria toEntity(OrderSearchCriteriaDto dto);

}
