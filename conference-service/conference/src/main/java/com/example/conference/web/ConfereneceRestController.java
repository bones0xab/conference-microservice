package com.example.conference.web;


import com.example.conference.Dtos.ConferenceRequestDTO;
import com.example.conference.Dtos.ConferenceResponseDTO;
import com.example.conference.entities.Conference;
import com.example.conference.mappers.ConferenceMapper;
import com.example.conference.serivce.ConferenceServiceImpl;
import com.example.conference.serivce.ConvferenceService;
import com.example.conference.serivce.ConvferenceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api") // Base path for all endpoints in this class
@RequiredArgsConstructor // Injects the service and mapper
public class ConfereneceRestController {

    private final ConvferenceService conferenceService; // Use the interface
    private final ConferenceMapper conferenceMapper;

    /**
     * GET /api/conferences
     * Gets all conferences.
     */
    @GetMapping("/conferences")
    public ResponseEntity<List<ConferenceResponseDTO>> getAllConferences() {
        List<Conference> conferences = conferenceService.getAllConferences();

        // Convert the list of entities to a list of DTOs
        List<ConferenceResponseDTO> dtos = conferences.stream()
                .map(conferenceMapper::toDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok(dtos);
    }

    /**
     * GET /api/conferences/{id}
     * Gets a single conference by its ID.
     */
    @GetMapping("/conferences/{id}")
    public ResponseEntity<ConferenceResponseDTO> getConferenceById(@PathVariable Long id) {
        Conference conference = conferenceService.getConferenceById(id);
        return ResponseEntity.ok(conferenceMapper.toDTO(conference));
    }

    /**
     * POST /api/conferences
     * Creates a new conference.
     */
    @PostMapping("/conferences")
    public ResponseEntity<ConferenceResponseDTO> addConference(@Valid @RequestBody ConferenceRequestDTO dto) {
        // 1. Service creates the entity
        Conference newConference = conferenceService.addConference(dto);

        // 2. Map the saved entity (with its new ID) to a response DTO
        ConferenceResponseDTO responseDTO = conferenceMapper.toDTO(newConference);

        // 3. Return a 201 CREATED status
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(responseDTO);
    }

    /**
     * PUT /api/conferences/{id}
     * Updates an existing conference.
     */
    @PutMapping("/conferences/{id}")
    public ResponseEntity<ConferenceResponseDTO> updateConference(@PathVariable Long id,
                                                                  @Valid @RequestBody ConferenceRequestDTO dto) {
        // 1. Service finds and updates the entity
        Conference updatedConference = conferenceService.updateConference(id, dto);

        // 2. Map the updated entity to a response DTO
        return ResponseEntity.ok(conferenceMapper.toDTO(updatedConference));
    }

    /**
     * DELETE /api/conferences/{id}
     * Deletes a conference by its ID.
     */
    @DeleteMapping("/conferences/{id}")
    public ResponseEntity<Void> deleteConference(@PathVariable Long id) {
        conferenceService.deleteConference(id);

        // Return a 204 NO CONTENT status
        return ResponseEntity.noContent().build();
    }
}