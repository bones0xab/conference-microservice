package com.example.demo.mapper;

import com.example.demo.Dtos.KeynoteRequestDTO;
import com.example.demo.Dtos.KeynoteResponseDTO;
import com.example.demo.entities.Keynote;
import org.springframework.stereotype.Component;


@Component // Makes this class a Spring Bean
public class KeyMapper {

    /**
     * Converts a KeynoteRequestDTO (from the client) into a Keynote entity.
     * This is used when CREATING a new Keynote.
     */
    public Keynote toEntity(KeynoteRequestDTO dto) {
        Keynote keynote = new Keynote();
        keynote.setNom(dto.getNom());
        keynote.setPrenom(dto.getPrenom());
        keynote.setEmail(dto.getEmail());
        keynote.setFonction(dto.getFonction());

        // We do NOT set the 'id' here.
        // The database will generate it upon saving.

        return keynote;
    }

    /**
     * Converts a Keynote entity (from the database) into a KeynoteResponseDTO.
     * This is used for all GET requests and as a response for POST/PUT.
     */
    public KeynoteResponseDTO toDTO(Keynote entity) {
        KeynoteResponseDTO dto = new KeynoteResponseDTO();
        dto.setId(entity.getId());
        dto.setNom(entity.getNom());
        dto.setPrenom(entity.getPrenom());
        dto.setEmail(entity.getEmail());
        dto.setFonction(entity.getFonction());
        return dto;
    }
}