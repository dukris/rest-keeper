package by.bsuir.restkeeper.service.builder;

import by.bsuir.restkeeper.domain.Statistics;

import java.util.List;

public interface Builder {

    String build(String path, List<Statistics> statistics);

}
