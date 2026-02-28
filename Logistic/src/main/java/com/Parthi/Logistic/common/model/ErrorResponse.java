package com.parthi.logistic.common.model;

import java.time.OffsetDateTime;
import org.springframework.format.annotation.DateTimeFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ErrorResponse {
    private String message;
    @DateTimeFormat(pattern = "dd-MM-yyyy hh:mm:ss")
    private OffsetDateTime date;

    public ErrorResponse(String message) {
        this.message = message;
        this.date = OffsetDateTime.now();
    }
}
