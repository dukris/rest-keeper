package by.bsuir.restkeeper.web.dto.mapper.criteria;

import by.bsuir.restkeeper.domain.criteria.UserSearchCriteria;
import by.bsuir.restkeeper.web.dto.criteria.UserSearchCriteriaDto;
import by.bsuir.restkeeper.web.dto.mapper.ObjectMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserSearchCriteriaMapper
        extends ObjectMapper<UserSearchCriteria, UserSearchCriteriaDto> {

}
