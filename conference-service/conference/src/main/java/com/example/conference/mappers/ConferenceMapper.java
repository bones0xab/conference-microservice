package com.example.conference.mappers;

import com.example.conference.Dtos.ConferenceRequestDTO;
import com.example.conference.Dtos.ConferenceResponseDTO;
import com.example.conference.entities.Conference;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor // Automatically injects ReviewMapper
public class ConferenceMapper {

    // Inject the ReviewMapper to handle the nested list
    private final ReviewMapper reviewMapper;


    public Conference toEntity(ConferenceRequestDTO dto) {
        Conference conference = new Conference();
        conference.setTitre(dto.getTitre());
        conference.setType(dto.getType());
        conference.setDate(dto.getDate());
        conference.setDuree(dto.getDuree());
        conference.setKeynoteIds(dto.getKeynoteIds());

        // Set defaults for calculated/server-managed fields
        conference.setNbInscrits(0);
        conference.setScore(0.0);
        conference.setReviews(new ArrayList<>()); // Initialize as empty list

        return conference;
    }


    public ConferenceResponseDTO toDTO(Conference entity) {
        ConferenceResponseDTO dto = new ConferenceResponseDTO();
        dto.setId(entity.getId());
        dto.setTitre(entity.getTitre());
        dto.setType(entity.getType());
        dto.setDate(entity.getDate());
        dto.setDuree(entity.getDuree());
        dto.setKeynoteIds(entity.getKeynoteIds());

        // Map calculated fields
        dto.setNbInscrits(entity.getNbInscrits());
        dto.setScore(entity.getScore());

        // Use the ReviewMapper to convert the list of Review entities
        if (entity.getReviews() != null) {
            dto.setReviews(
                    entity.getReviews().stream()
                            .map(reviewMapper::toDTO) // Delegate to ReviewMapper
                            .collect(Collectors.toList())
            );
        }
        return dto;
    }
}