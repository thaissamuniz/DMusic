package com.example.dmusic.controller.dto;

import com.example.dmusic.model.Playlist;
import com.example.dmusic.model.Track;

import java.util.List;

public record PlaylistDTO(String id, int userId, String name, List<Track> tracks, List<String> tags) {
    static public PlaylistDTO fromModel(Playlist playlist) {
        return new PlaylistDTO(playlist.getId(), playlist.getUserId(), playlist.getName(), playlist.getTracks(), playlist.getTags());
    }
}
