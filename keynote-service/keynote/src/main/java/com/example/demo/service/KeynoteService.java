package com.example.demo.service;

import com.example.demo.Dtos.KeynoteRequestDTO;
import com.example.demo.entities.Keynote;
import com.example.demo.repository.KeynoteRepository;
import jakarta.ws.rs.Path;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface KeynoteService {
    List<Keynote> getAllKeynotes();

    Keynote getKeynoteById(String id);

    Keynote addKeynote(KeynoteRequestDTO dto);
    Keynote updateKeynote(String id,  KeynoteRequestDTO dto);
    void deleteKeynoteById(String id);
}
