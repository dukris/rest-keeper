package by.bsuir.restkeeper.service.builder.impl;

import by.bsuir.restkeeper.domain.Statistics;
import by.bsuir.restkeeper.service.builder.Builder;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CsvBuilder implements Builder {

    private static final String[] HEADER =
            {"Daily revenue", "Monthly revenue", "Average bill",
                    "Daily amount of guests", "Amount of guests in the morning",
                    "Amount of guests in the evening", "The most popular dish"};

    @Override
    @SneakyThrows
    public String build(String path, List<Statistics> statistics) {
        String filename = this.prepareFilename(path);
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(filename));
             CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.Builder
                     .create()
                     .setHeader(HEADER)
                     .build())) {
            for (Statistics record :statistics) {
                csvPrinter.printRecord(Arrays.asList(
                        record.getDailyRevenue(),
                        record.getMonthlyRevenue(),
                        record.getAverageBill(),
                        record.getDailyAmountOfGuests(),
                        record.getFirstHalfAmountOfGuests(),
                        record.getLastHalfAmountOfGuests(),
                        record.getDailyDish().getName()));
            }
            csvPrinter.flush();
        }
        return filename;
    }

    private String prepareFilename(String path) {
        return path + " " + LocalDate.now() + ".csv";
    }

}
