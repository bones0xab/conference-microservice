package com.example.conference.model;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Keynote {
    private String nom;
    private String prenom;
    private String email;
    private String fonction;
}
