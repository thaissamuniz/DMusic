package com.example.dmusic.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Playlist {
    private String name;
    private List<Track> tracks;

    public Playlist(){
        tracks = new ArrayList<>();
    }

}