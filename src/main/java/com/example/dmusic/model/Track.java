package com.example.dmusic.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Track {
    private String id;
    private String title;
    private Artist artist;
    private Album album;
}
