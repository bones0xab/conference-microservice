package com.example.demo.service;

// In your KeynoteServiceImpl.java

import com.example.demo.Dtos.KeynoteRequestDTO;
import com.example.demo.entities.Keynote;
import com.example.demo.mapper.KeyMapper;
import com.example.demo.repository.KeynoteRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class KeynoteServiceImpl implements KeynoteService {

    private final KeynoteRepository keynoteRepository;
    private final KeyMapper keynoteMapper; // <-- Inject the mapper

    @Override
    public List<Keynote> getAllKeynotes() {
        return keynoteRepository.findAll();
    }

    @Override
    public Keynote getKeynoteById(String id) {
        return keynoteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Keynote not found"));
    }

    @Override
    public Keynote addKeynote(KeynoteRequestDTO dto) {
        // Use the mapper to create the entity
        Keynote keynote = keynoteMapper.toEntity(dto);
        return keynoteRepository.save(keynote);
    }

    @Override
    public Keynote updateKeynote(String id, KeynoteRequestDTO dto) {
        // 1. Find the existing keynote
        Keynote existingKeynote = getKeynoteById(id);

        // 2. Update its fields from the DTO
        existingKeynote.setNom(dto.getNom());
        existingKeynote.setPrenom(dto.getPrenom());
        existingKeynote.setEmail(dto.getEmail());
        existingKeynote.setFonction(dto.getFonction());

        // 3. Save the updated entity
        return keynoteRepository.save(existingKeynote);
    }

    @Override
    public void deleteKeynoteById(String id) {
        // First check if it exists
        if (!keynoteRepository.existsById(id)) {
            throw new EntityNotFoundException("Keynote not found");
        }
        keynoteRepository.deleteById(id);
    }
}