package com.example.demo.repository;

import com.example.demo.entities.Keynote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KeynoteRepository extends JpaRepository<Keynote,String> {
}
