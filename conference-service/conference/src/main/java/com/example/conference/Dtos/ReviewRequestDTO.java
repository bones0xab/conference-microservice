package com.example.conference.Dtos;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ReviewRequestDTO {

    @NotBlank(message = "Review text cannot be blank")
    @Size(max = 2000, message = "Review text cannot exceed 2000 characters")
    private String texte;

    @Min(value = 1, message = "Note must be at least 1")
    @Max(value = 5, message = "Note must be at most 5")
    private int note;
}