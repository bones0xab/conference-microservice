package com.example.conference.Dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
public class ReviewResponseDTO {
    private Long id;
    private Date date;
    private String texte;
    private int note;
}
