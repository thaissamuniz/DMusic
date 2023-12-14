package com.example.dmusic.service;

import com.example.dmusic.exception.ResourceNotFoundException;
import com.example.dmusic.model.Playlist;
import com.example.dmusic.model.Track;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PlaylistServiceTest {
    @Autowired
    PlaylistService playlistService;
    Track track = new Track();

    @Test
    void createTest() {
        Playlist playlist = new Playlist(1, "pop", List.of("festa", "animadas"), List.of(track));
        Playlist playlistTest = playlistService.create(playlist);
        assertNotNull(playlistTest.getId());
        assertEquals("pop", playlistTest.getName());
        assertNotEquals(0, playlistTest.getTracks().size());
    }

    @Test
    void getAllTest() {
        List<Playlist> playlists = playlistService.getAll("pop", "festa");
        assertNotNull(playlists);
    }

    @Test
    void updateTest() {
        Playlist playlist = new Playlist(1, "pop", List.of("festa", "animadas"), List.of(track));
        playlistService.create(playlist);

        playlistService.update(playlist);
    }

    @Test
    void deleteTest() {
        Playlist playlist = new Playlist(2, "rock", List.of("rock", "amigos"), List.of(track));
        Playlist playlistTest = playlistService.create(playlist);
        playlistService.delete(playlistTest.getId());
        List<Playlist> playlists = playlistService.getAll("", "");
        assertFalse(playlists.contains(playlistTest));
    }

    @Test
    void deleteTestException() {
        Playlist playlist = new Playlist(1, "pop", List.of("festa", "animadas"), List.of(track));
        playlistService.create(playlist);
        assertThrows(ResourceNotFoundException.class, () -> playlistService.delete("5cf773dc-e919-4aad-aedd-b006b5845433"));
    }

}
