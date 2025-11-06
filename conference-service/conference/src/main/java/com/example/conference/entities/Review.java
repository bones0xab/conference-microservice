package com.example.conference.entities;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date date;

    @Column(length = 2000)
    private String texte;

    private int note; // 1 to 5

    @ManyToOne
    @JoinColumn(name = "conference_id")
    private Conference conference;
}
