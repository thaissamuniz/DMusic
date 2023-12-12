package com.example.dmusic.controller;

import com.example.dmusic.model.Playlist;
import com.example.dmusic.model.Track;
import com.example.dmusic.service.PlaylistService;
import com.example.dmusic.service.TrackService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/playlist")
@Slf4j
public class PlaylistController {
    private Playlist playlist;
    @Autowired
    TrackService trackService;
    @Autowired
    PlaylistService playlistService;

    @PostMapping
    public void create() {
        playlist = playlistService.create();
        System.out.println("playlist criada com sucesso.");
    }

    @PutMapping
    public void addTrack(@RequestBody String id) {

        String re = id.substring(12, 20);

        String idModificado = re.replaceAll("\"", "");
        System.out.println(idModificado);


        Track trs = trackService.getTrackById(idModificado);
        System.out.println(trs);

//        playlistService.addTrack(id, trackService, playlist.getTracks());
        System.out.println("musica adicionada com sucesso!");
//        try {
//            playlistService.addTrack(id, trackService, playlist.getTracks());
//            System.out.println("musica adcionada com sucesso!");
//        }catch (){
//
//        }
    }

    @DeleteMapping("/{id}")
    public void deleteTrack(@PathVariable String id) {
        playlistService.deleteTrack(id, trackService, playlist.getTracks());
        System.out.println("musica removida com sucesso!");
    }

    @GetMapping
    public ResponseEntity getAll() {
        try {
            if (playlist.getTracks().isEmpty()) {
                return ResponseEntity.ok("Playlist Vazia.");
            } else {
                List<Track> tracks = playlistService.getAll(playlist.getTracks());
                return ResponseEntity.ok(tracks);
            }
        } catch (NullPointerException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Você ainda não tem uma playlist. Crie uma e tente novamente.");
        }
    }
}
