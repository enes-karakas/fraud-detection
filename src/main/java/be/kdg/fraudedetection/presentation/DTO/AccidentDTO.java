package be.kdg.fraudedetection.presentation.DTO;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

public class AccidentDTO {
    @NotEmpty
    private String name;

    @NotEmpty
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private String date;


    public AccidentDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
