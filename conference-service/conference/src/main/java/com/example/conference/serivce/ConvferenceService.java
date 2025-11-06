package com.example.conference.serivce;

import com.example.conference.Dtos.ConferenceRequestDTO;
import com.example.conference.JPA.ConferenceRepository;
import com.example.conference.entities.Conference;
import com.example.conference.mappers.ConferenceMapper;
import org.springframework.stereotype.Service;

import java.util.List;



public interface ConvferenceService {
    List<Conference> getAllConferences();
    Conference getConferenceById(Long id);
    Conference addConference(ConferenceRequestDTO dto);
    Conference updateConference(Long id, ConferenceRequestDTO dto);
    void deleteConference(Long id);
}
