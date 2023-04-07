package by.bsuir.restkeeper.web.dto.mapper;

import by.bsuir.restkeeper.domain.Statistics;
import by.bsuir.restkeeper.web.dto.StatisticsDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StatisticsMapper {

    StatisticsDto toDto(Statistics entity);

}
