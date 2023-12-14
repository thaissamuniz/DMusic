package com.example.dmusic.model;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class Playlist {
    private String id;
    private int userId;
    private String name;
    private List<Track> tracks;
    private List<String> tags;

    public Playlist(int userId, String name, List<String> tags, List<Track> tracks) {
        this.userId = userId;
        this.name = name;
        this.tags = tags;
        this.tracks = tracks;
    }

    public Playlist(String name, List<String> tags, List<Track> tracks) {
        this.name = name;
        this.tags = tags;
        this.tracks = tracks;
    }

    public void generateId() {
        this.id = UUID.randomUUID().toString();
    }
    public void addTrack(Track track){
        this.tracks.add(track);
    }
}