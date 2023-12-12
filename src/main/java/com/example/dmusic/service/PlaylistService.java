package com.example.dmusic.service;

import com.example.dmusic.model.Playlist;
import com.example.dmusic.model.Track;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaylistService {

    public Playlist create(){
        return new Playlist();
    }

    public List<Track> getAll(List<Track> playlist){
        return playlist;
    }

    public void addTrack(String id, TrackService trackService, List<Track> tracks) {
        Track track = trackService.getTrackById(id);
        if(track.getId() != null) {
            tracks.add(track);
        }
    }

    public void add(String id, List<Track> tracks, TrackService trackService) {
        Track track = trackService.getTrackById(id);
        tracks.add(track);
    }

    public void deleteTrack(String id, TrackService trackService, List<Track> tracks) {
        Track track = trackService.getTrackById(id);
        if(track != null) {
            tracks.remove(track);
        }
    }
}
