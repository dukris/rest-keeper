package by.bsuir.restkeeper.web.dto.mapper.criteria;

import by.bsuir.restkeeper.domain.criteria.OrderSearchCriteria;
import by.bsuir.restkeeper.web.dto.criteria.OrderSearchCriteriaDto;
import by.bsuir.restkeeper.web.dto.mapper.ObjectMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderSearchCriteriaMapper
extends ObjectMapper<OrderSearchCriteria, OrderSearchCriteriaDto> {

}
