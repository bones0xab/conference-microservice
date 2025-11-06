package com.example.conference;

import com.example.conference.JPA.ConferenceRepository;
import com.example.conference.entities.Conference;
import com.example.conference.entities.Review;
import com.example.conference.enums.ConferenceType;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootApplication
@EnableFeignClients
public class ConferenceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConferenceApplication.class, args);
	}

    @Bean
    CommandLineRunner initDatabase(ConferenceRepository conferenceRepository) {

        return args -> {
            // --- Conference 1 (with 2 reviews) ---
            Conference conf1 = Conference.builder()
                    .titre("Spring Boot Intro")
                    .type(ConferenceType.ACADEMIQUE)
                    .date(new Date())
                    .duree(120)
                    .nbInscrits(50)
                    .score(4.5) // We set this manually for testing
                    .keynoteIds(List.of("keynote-1", "keynote-2"))
                    .reviews(new ArrayList<>()) // Init the list
                    .build();

            // Create reviews
            Review review1_1 = Review.builder()
                    .date(new Date())
                    .texte("Great intro workshop!")
                    .note(5)
                    .conference(conf1) // Link review to conference
                    .build();

            Review review1_2 = Review.builder()
                    .date(new Date())
                    .texte("A bit too basic.")
                    .note(4)
                    .conference(conf1) // Link review to conference
                    .build();

            // Add reviews to the conference's list
            conf1.getReviews().addAll(List.of(review1_1, review1_2));


            // --- Conference 2 (with 1 review) ---
            Conference conf2 = Conference.builder()
                    .titre("Angular Deep Dive")
                    .type(ConferenceType.COMMERCIALE)
                    .date(new Date())
                    .duree(180)
                    .nbInscrits(75)
                    .score(5.0)
                    .keynoteIds(List.of("keynote-3"))
                    .reviews(new ArrayList<>())
                    .build();

            Review review2_1 = Review.builder()
                    .date(new Date())
                    .texte("Amazing content!")
                    .note(5)
                    .conference(conf2)
                    .build();

            conf2.getReviews().add(review2_1);


            // --- Conference 3 (no reviews) ---
            Conference conf3 = Conference.builder()
                    .titre("Microservices")
                    .type(ConferenceType.ACADEMIQUE)
                    .date(new Date())
                    .duree(360)
                    .nbInscrits(200)
                    .score(0.0) // No reviews yet
                    .keynoteIds(List.of("keynote-1", "keynote-4"))
                    .reviews(new ArrayList<>())
                    .build();

            // --- Conference 4 (no reviews) ---
            Conference conf4 = Conference.builder()
                    .titre("Intro to LLMs")
                    .type(ConferenceType.COMMERCIALE)
                    .date(new Date())
                    .duree(150)
                    .nbInscrits(40)
                    .score(0.0)
                    .keynoteIds(new ArrayList<>())
                    .reviews(new ArrayList<>())
                    .build();


            // --- Save All ---
            // Save all conferences to the repository.
            // Because of CascadeType.ALL, the reviews will be saved automatically.
            conferenceRepository.saveAll(List.of(conf1, conf2, conf3, conf4));
        };
    }
}
