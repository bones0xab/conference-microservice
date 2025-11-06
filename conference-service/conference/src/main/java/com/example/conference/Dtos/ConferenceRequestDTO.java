package com.example.conference.Dtos;

import com.example.conference.enums.ConferenceType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class ConferenceRequestDTO {

    @NotBlank(message = "Title cannot be blank")
    private String titre;

    @NotNull(message = "Conference type must be specified")
    private ConferenceType type;

    @NotBlank(message = "Date is required")
    private Date date;

    @Min(value = 1, message = "Duration must be at least 1 minute")
    private int duree;

    // It's okay to accept an initial list of keynote IDs
    private List<String> keynoteIds;
}