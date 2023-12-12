package com.example.dmusic.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
public class Playlist {
    private String id;
    private int userId;
    private String name;
    private List<Track> tracks;
    private List<String> tags;

    public Playlist(int userId, String name, List<String> tags){
        this.id = UUID.randomUUID().toString();
        this.userId = userId;
        this.name = name;
        this.tracks = new ArrayList<>();
        this.tags = tags;
    }
}