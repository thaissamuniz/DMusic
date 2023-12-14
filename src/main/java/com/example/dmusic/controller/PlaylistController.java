package com.example.dmusic.controller;

import com.example.dmusic.controller.dto.PlaylistDTO;
import com.example.dmusic.controller.dto.PlaylistUpdateDTO;
import com.example.dmusic.exception.ResourceNotFoundException;
import com.example.dmusic.model.Playlist;
import com.example.dmusic.controller.dto.PlaylistCreteDTO;
import com.example.dmusic.service.PlaylistService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/playlist")
@Slf4j
public class PlaylistController {
    private final PlaylistService playlistService;

    public PlaylistController(PlaylistService playlistService) {
        this.playlistService = playlistService;
    }

    @GetMapping
    public ResponseEntity<List<PlaylistDTO>> getAll(@RequestParam(required = false, defaultValue = "") String name, @RequestParam(required = false, defaultValue = "") String tag) {
        List<Playlist> playlists = playlistService.getAll(name, tag);
        List<PlaylistDTO> dtos = playlists.stream().map(PlaylistDTO::fromModel).toList();
        return ResponseEntity.ok(dtos);
    }

    @PostMapping
    public ResponseEntity<PlaylistDTO> create(@RequestBody @Valid PlaylistCreteDTO playlistCreteDTO) {
        Playlist playlist = playlistService.create(playlistCreteDTO.toModel());
        PlaylistDTO dto = PlaylistDTO.fromModel(playlist);
        log.info("Playlist {} criada com sucesso!", playlist.getId());
        return ResponseEntity.status(HttpStatus.CREATED).location(URI.create("/playlist/" + playlist.getId())).body(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable String id, @RequestBody PlaylistUpdateDTO playlistDTO) {
        try {
            Playlist playlist = playlistDTO.toModel();
            playlist.setId(id);
            playlistService.update(playlist);
            log.info("Playlist {} atualizada com sucesso!", id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhuma playlist com este ID");

        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable String id) {
        try {
            playlistService.delete(id);
            log.info("Playlist {} deletada com sucesso!", id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhuma playlist com este ID");
        }
    }
}
