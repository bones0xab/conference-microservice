package com.example.conference.feign;

import com.example.conference.model.Keynote;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "keynote-service")
public interface KeynoteRestClient {
    @GetMapping("/keynotes")
    List<Keynote> findAllKeynotes();

    @GetMapping("/keynotes/{id}")
    Keynote findKeynoteById(@PathVariable("id") String id);
}
