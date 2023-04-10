package by.bsuir.restkeeper.web.controller;

import by.bsuir.restkeeper.domain.Statistics;
import by.bsuir.restkeeper.service.StatisticsService;
import by.bsuir.restkeeper.web.dto.StatisticsDto;
import by.bsuir.restkeeper.web.dto.mapper.StatisticsMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/restkeeper/v1/statistics")
public class StatisticsController {

    private final StatisticsService statisticsService;
    private final StatisticsMapper statisticsMapper;

    @GetMapping
    public StatisticsDto get() {
        Statistics statistics = this.statisticsService.getStatistics();
        return this.statisticsMapper.toDto(statistics);
    }

    //todo download statistics

}
