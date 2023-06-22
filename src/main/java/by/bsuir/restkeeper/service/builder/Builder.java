package by.bsuir.restkeeper.service.builder;

import by.bsuir.restkeeper.domain.Statistics;

import java.util.List;

public interface Builder {

    /**
     * Build csv file.
     *
     * @param path Path
     * @param statistics List of statistics
     * @return Path
     */
    String build(String path, List<Statistics> statistics);

}
