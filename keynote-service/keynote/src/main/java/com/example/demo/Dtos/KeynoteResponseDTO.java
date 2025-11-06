package com.example.demo.Dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO for sending Keynote details to the client.
 * Includes all fields, including the server-generated 'id'.
 */
@Data
@NoArgsConstructor
public class KeynoteResponseDTO {

    private String id;
    private String nom;
    private String prenom;
    private String email;
    private String fonction;
}
