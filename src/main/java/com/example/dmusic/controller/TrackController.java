package com.example.dmusic.controller;

import com.example.dmusic.model.Track;
import com.example.dmusic.service.TrackService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping("/track")
@Slf4j
public class TrackController {

    private final TrackService service;

    public TrackController(TrackService trackService) {
        this.service = trackService;
    }

    @GetMapping
    public ResponseEntity<List<Track>> getAll(@RequestParam String name) {
        List<Track> tracks = service.searchTrackByName(name);
        return ResponseEntity.ok(tracks);
    }
}
