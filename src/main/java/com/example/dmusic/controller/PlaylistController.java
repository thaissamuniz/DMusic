package com.example.dmusic.controller;

import com.example.dmusic.model.Playlist;
import com.example.dmusic.model.Track;
import com.example.dmusic.playlistDTO.PlaylistDTO;
import com.example.dmusic.service.PlaylistService;
import com.example.dmusic.service.TrackService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/playlist")
@Slf4j
public class PlaylistController {
    private final TrackService trackService;
    private final PlaylistService playlistService;

    public PlaylistController(TrackService trackService, PlaylistService playlistService) {
        this.trackService = trackService;
        this.playlistService = playlistService;
    }

    @PostMapping
    public ResponseEntity<String> create(@RequestBody @Valid PlaylistDTO playlistDTO) {
        playlistService.create(playlistDTO.userId(), playlistDTO.name(), playlistDTO.tags());
        return ResponseEntity.status(HttpStatus.CREATED).body("Playlist criada com sucesso!");
    }

    @PutMapping
    public ResponseEntity<String> addTrack(@RequestBody Map<String, Object> id) {
        try {
            String idStr = (String) id.get("id");
            Track track = playlistService.addTrack(idStr, trackService);
            if (track != null) return ResponseEntity.ok("Música adicionada com sucesso!");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Música não localizada. Verifique o id e tente novamente.");
        } catch (RuntimeException ex) {
            log.error(ex.getMessage());
            return null;
        }
    }

    @DeleteMapping
    public ResponseEntity<String> deleteTrack(@RequestBody Map<String, Object> id) {
        try {
            String idStr = (String) id.get("id");
            Track track = playlistService.deleteTrack(idStr, trackService);
            if (track != null) return ResponseEntity.ok("Música removida com sucesso!");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Música não localizada. Verifique o id e tente novamente.");
        } catch (RuntimeException ex) {
            log.error(ex.getMessage());
            return null;
        }
    }

    @GetMapping
    public ResponseEntity getAll() {
        try {
            if (playlistService.getAll() == null) {
                return ResponseEntity.ok("Playlist Vazia.");
            } else {
                Playlist playlist = playlistService.getAll();
                List<Track> tracks = playlist.getTracks();
                return ResponseEntity.ok("PLAYLIST: " + playlist.getName().toUpperCase() + "\n"+ tracks);
            }
        } catch (NullPointerException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Você ainda não tem uma playlist. Crie uma e tente novamente.");
        }
    }
}
