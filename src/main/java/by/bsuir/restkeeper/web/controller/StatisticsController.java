package by.bsuir.restkeeper.web.controller;

import by.bsuir.restkeeper.domain.Statistics;
import by.bsuir.restkeeper.service.FileService;
import by.bsuir.restkeeper.service.StatisticsService;
import by.bsuir.restkeeper.service.builder.Builder;
import by.bsuir.restkeeper.web.dto.StatisticsDto;
import by.bsuir.restkeeper.web.dto.mapper.StatisticsMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.MimeType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/restkeeper/v1/statistics")
public class StatisticsController {

    private final StatisticsService statisticsService;
    private final FileService fileService;
    private final StatisticsMapper statisticsMapper;
    private final Builder builder;

    @GetMapping
    @PreAuthorize("@securityExpressions.hasAdminRole()")
    public StatisticsDto get() {
        Statistics statistics = this.statisticsService.getStatistics();
        return this.statisticsMapper.toDto(statistics);
    }

    @GetMapping("/download")
    public ResponseEntity<Resource> download() {
        Statistics statistics = this.statisticsService.getStatistics();
        String filename = this.builder.build("report", List.of(statistics));
        ResponseEntity<Resource> response = ResponseEntity.ok()
                .contentType(MediaType.asMediaType(MimeType.valueOf("application/vnd.ms-excel")))
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + filename + "\"")
                .body(this.fileService.download(filename));
        this.fileService.delete(filename);
        return response;
    }

}
