package com.example.dmusic.controller.dto;

import com.example.dmusic.model.Playlist;
import com.example.dmusic.model.Track;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record PlaylistUpdateDTO(

        @NotBlank
        String name,
        @NotEmpty
        List<String> tags,

        @NotNull
        List<Track> tracks
) {
        public Playlist toModel() {
                return new Playlist(name, tags, tracks);
        }
}
