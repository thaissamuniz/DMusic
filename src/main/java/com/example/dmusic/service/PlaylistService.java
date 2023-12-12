package com.example.dmusic.service;

import com.example.dmusic.model.Playlist;
import com.example.dmusic.model.Track;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PlaylistService {
    private Playlist playlist;

    public void create(int userId, String name, List<String> tags ) {
        this.playlist = new Playlist(userId, name, tags);
    }

    public Playlist getAll() {
        if(this.playlist.getTracks().isEmpty()){
            return null;
        }
        return this.playlist;
    }

    public Track addTrack(String id, TrackService trackService) {
        Track track = trackService.getTrackById(id);
        if (track.getId() != null) {
            this.playlist.getTracks().add(track);
            return track;
        }
        return null;
    }

    public Track deleteTrack(String id, TrackService trackService) {
        Track track = trackService.getTrackById(id);
        if (track.getId() != null) {
            this.playlist.getTracks().remove(track);
            return track;
        }
        return null;
    }
}
