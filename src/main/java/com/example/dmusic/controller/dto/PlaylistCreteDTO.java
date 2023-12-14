package com.example.dmusic.controller.dto;

import com.example.dmusic.model.Playlist;
import com.example.dmusic.model.Track;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.List;

public record PlaylistCreteDTO(
        @Positive
        int userId,
        @NotBlank
        String name,
        @NotEmpty
        List<String> tags,
        @NotNull
        List<String> tracksIds
) {

    public Playlist toModel() {
        List<Track> tracks = tracksIds.stream().map(Track::new).toList();
        return new Playlist(userId, name, tags, tracks);
    }
}
