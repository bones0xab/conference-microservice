package com.example.conference.mappers;

import com.example.conference.Dtos.ReviewRequestDTO;
import com.example.conference.Dtos.ReviewResponseDTO;
import com.example.conference.entities.Conference;
import com.example.conference.entities.Review;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;

@Component
public class ReviewMapper {

    /**
     * Converts a ReviewRequestDTO to a Review entity.
     * The Conference must be set separately in the service layer.
     */
    public Review toEntity(ReviewRequestDTO dto, Conference conference) {
        Review review = new Review();
        review.setTexte(dto.getTexte());
        review.setNote(dto.getNote());

        // Set server-side values
        review.setDate(new Date());
        review.setConference(conference); // Link to the parent conference

        return review;
    }

    /**
     * Converts a Review entity to a ReviewResponseDTO.
     */
    public ReviewResponseDTO toDTO(Review entity) {
        ReviewResponseDTO dto = new ReviewResponseDTO();
        dto.setId(entity.getId());
        dto.setDate(entity.getDate());
        dto.setTexte(entity.getTexte());
        dto.setNote(entity.getNote());
        return dto;
    }
}