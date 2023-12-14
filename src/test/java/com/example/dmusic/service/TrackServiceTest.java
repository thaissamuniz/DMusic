package com.example.dmusic.service;

import com.example.dmusic.model.Track;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TrackServiceTest {
    @Autowired
    TrackService trackService;
    @Test
    void searchTrackByNameTest() {
        List<Track> tracks = trackService.searchTrackByName("amor");
        assertNotNull(tracks);
        assertEquals("amor", tracks.get(0).getTitle().toLowerCase());
    }

    @Test
    void getTrackByIdTest() {
        Track track = trackService.getTrackById("1254476972");
        assertEquals("1254476972", track.getId());
    }
}