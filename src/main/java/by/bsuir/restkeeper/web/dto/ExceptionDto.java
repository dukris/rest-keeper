package by.bsuir.restkeeper.web.dto;

import lombok.Builder;

@Builder
public record ExceptionDto(

        String field,
        String message

) {
}
