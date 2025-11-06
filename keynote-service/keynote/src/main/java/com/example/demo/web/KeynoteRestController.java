package com.example.demo.web;

import com.example.demo.Dtos.KeynoteRequestDTO;
import com.example.demo.Dtos.KeynoteResponseDTO;
import com.example.demo.entities.Keynote;
import com.example.demo.mapper.KeyMapper;
import com.example.demo.service.KeynoteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api") // Base path for all endpoints
@RequiredArgsConstructor
public class KeynoteRestController {

    private final KeynoteService keynoteService;
    private final KeyMapper keynoteMapper;

    /**
     * GET /api/keynotes
     * Retrieves a list of all keynotes.
     */
    @GetMapping("/keynotes")
    public ResponseEntity<List<KeynoteResponseDTO>> getAllKeynotes() {
        List<Keynote> keynotes = keynoteService.getAllKeynotes();

        // Map the list of entities to a list of ResponseDTOs
        List<KeynoteResponseDTO> dtos = keynotes.stream()
                .map(keynoteMapper::toDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok(dtos);
    }

    /**
     * GET /api/keynotes/{id}
     * Retrieves a single keynote by its ID.
     */
    @GetMapping("/keynotes/{id}")
    public ResponseEntity<KeynoteResponseDTO> getKeynoteById(@PathVariable String id) {
        Keynote keynote = keynoteService.getKeynoteById(id);

        // Map the entity to a ResponseDTO
        return ResponseEntity.ok(keynoteMapper.toDTO(keynote));
    }

    /**
     * POST /api/keynotes
     * Creates a new keynote.
     */
    @PostMapping("/keynotes")
    public ResponseEntity<KeynoteResponseDTO> addKeynote(@Valid @RequestBody KeynoteRequestDTO dto) {
        // 1. Service creates the entity
        Keynote newKeynote = keynoteService.addKeynote(dto);

        // 2. Map the saved entity (now with an ID) to a ResponseDTO
        KeynoteResponseDTO responseDTO = keynoteMapper.toDTO(newKeynote);

        // 3. Return a 201 CREATED status
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(responseDTO);
    }

    /**
     * PUT /api/keynotes/{id}
     * Updates an existing keynote.
     */
    @PutMapping("/keynotes/{id}")
    public ResponseEntity<KeynoteResponseDTO> updateKeynote(@PathVariable String id,
                                                            @Valid @RequestBody KeynoteRequestDTO dto) {
        // 1. Service finds and updates the entity
        Keynote updatedKeynote = keynoteService.updateKeynote(id, dto);

        // 2. Map the updated entity to a ResponseDTO
        return ResponseEntity.ok(keynoteMapper.toDTO(updatedKeynote));
    }

    /**
     * DELETE /api/keynotes/{id}
     * Deletes a keynote by its ID.
     */
    @DeleteMapping("/keynotes/{id}")
    public ResponseEntity<Void> deleteKeynote(@PathVariable String id) {
        keynoteService.deleteKeynoteById(id);

        // 2. Return a 204 NO CONTENT status
        return ResponseEntity.noContent().build();
    }
}