package com.example.dmusic.playlistDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;

import java.util.List;

public record PlaylistDTO(
        @Positive
        int userId,
        @NotBlank
        String name,
        @NotEmpty
        List<String> tags) {
}
