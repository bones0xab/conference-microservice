package com.example.conference.Dtos;

import com.example.conference.enums.ConferenceType;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class ConferenceResponseDTO {

    private Long id;
    private String titre;
    private ConferenceType type;
    private Date date;
    private int duree;

    // Include calculated fields in the response
    private int nbInscrits;
    private double score;

    private List<String> keynoteIds;

    // Use the DTO for the nested list
    private List<ReviewResponseDTO> reviews;
}