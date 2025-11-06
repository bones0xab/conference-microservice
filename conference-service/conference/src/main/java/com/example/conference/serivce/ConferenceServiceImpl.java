package com.example.conference.serivce;

import com.example.conference.Dtos.ConferenceRequestDTO;
import com.example.conference.Dtos.ConferenceResponseDTO;
import com.example.conference.JPA.ConferenceRepository;
import com.example.conference.entities.Conference;
import com.example.conference.mappers.ConferenceMapper;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service // Tells Spring this is a service
@RequiredArgsConstructor // Injects the repository
@Transactional // Makes all methods transactional
public class ConferenceServiceImpl implements ConvferenceService { // Note: 'ConferenceService' spelling corrected

    private final ConferenceRepository conferenceRepository;
    private final ConferenceMapper conferenceMapper;

    @Override
    public List<Conference> getAllConferences() {
        return conferenceRepository.findAll();
    }

    @Override
    public Conference getConferenceById(Long id) {
        return conferenceRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Conference not found with id: " + id));
    }

    @Override
    public Conference addConference(ConferenceRequestDTO dto) {
        return conferenceRepository.save(conferenceMapper.toEntity(dto));
    }

    @Override
    public Conference updateConference(Long id, ConferenceRequestDTO dto) {
        // 1. Find the existing conference
        Conference existingConference = getConferenceById(id); // Re-use our find method

        // 2. Update only the fields from the DTO
        // This protects your calculated fields like score and nbInscrits
        existingConference.setTitre(dto.getTitre());
        existingConference.setType(dto.getType());
        existingConference.setDate(dto.getDate());
        existingConference.setDuree(dto.getDuree());
        existingConference.setKeynoteIds(dto.getKeynoteIds());

        // 3. Save the updated entity
        return conferenceRepository.save(existingConference);
    }


    @Override
    public void deleteConference(Long id) {
        if (!conferenceRepository.existsById(id)) {
            throw new EntityNotFoundException("Conference not found with id: " + id);
        }
        conferenceRepository.deleteById(id);
    }
}