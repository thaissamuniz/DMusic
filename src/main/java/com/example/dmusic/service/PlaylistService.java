package com.example.dmusic.service;

import com.example.dmusic.model.Playlist;
import com.example.dmusic.model.Track;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PlaylistService {
    private final Map<String, Playlist> playlists = new HashMap<>();
    private final TrackService trackService;

    public PlaylistService(TrackService trackService) {
        this.trackService = trackService;
    }

    public Playlist create(Playlist playlist) {
        playlist.generateId();
        fillTracksDetail(playlist);
        playlists.put(playlist.getId(), playlist);
        return playlist;
    }

    private void fillTracksDetail(Playlist playlist) {
        for (Track track : playlist.getTracks()) {
            Track trackById = trackService.getTrackById(track.getId());
            track.setAlbum(trackById.getAlbum());
            track.setArtist(trackById.getArtist());
            track.setTitle(trackById.getTitle());
        }
    }

    public List<Playlist> getAll(String name, String tag) {
        return this.playlists.values()
                .stream()
                .filter(playlist -> {
                    if (name.isBlank()) return true;
                    return playlist.getName().equals(name);
                })
                .filter(playlist -> {
                    if (tag.isBlank()) return true;
                    return playlist.getTags().contains(tag);
                }).toList();
    }

    public void update(Playlist playlist) {
        Playlist playlistToUpdate = this.playlists.get(playlist.getId());
        playlistToUpdate.setName(playlist.getName());
        playlistToUpdate.setTags(playlist.getTags());
        playlistToUpdate.setTracks(playlist.getTracks());
    }

    public void delete(String id) {
        this.playlists.remove(id);
    }
}
