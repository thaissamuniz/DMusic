package com.example.dmusic.service;

import com.example.dmusic.model.Track;
import com.example.dmusic.model.DeezerTrackResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

@Service
@Slf4j
public class TrackService {
    private static final String URL = "https://api.deezer.com";
    private final ObjectMapper mapper = JsonMapper.builder().addModule(new JavaTimeModule()).build();

    public List<Track> searchTrackByName(String name) {
        String uri = URL + "/search/track?q=" + name;
        try {
            HttpRequest request = getHttpRequest(uri);
            HttpClient client = getHttpClient();
            HttpResponse<String> response = getHttpResponse(client, request);
            ObjectMapper mapper = JsonMapper.builder().addModule(new JavaTimeModule()).build();
            var deezerResponse = mapper.readValue(response.body(), DeezerTrackResponse.class);
            return deezerResponse.getData();
        } catch (URISyntaxException | IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public Track getTrackById(String id) {
        String uri = URL + "/track/" + id;
        try {
            HttpRequest request = getHttpRequest(uri);
            HttpClient client = getHttpClient();
            HttpResponse<String> response = getHttpResponse(client, request);
            var trackResponse = mapper.readValue(response.body(), Track.class);
            log.info("trackResponse: {}", trackResponse);
            return trackResponse;
        } catch (URISyntaxException | IOException | InterruptedException e) {
            log.error("", e);
            throw new RuntimeException(e);
        }
    }

    private static HttpRequest getHttpRequest(String uri) throws URISyntaxException {
        return HttpRequest.newBuilder()
                .GET()
                .uri(new URI(uri))
                .version(HttpClient.Version.HTTP_2)
                .build();
    }

    private static HttpClient getHttpClient() {
        return HttpClient.newBuilder().build();
    }

    private static HttpResponse<String> getHttpResponse(HttpClient client, HttpRequest request) throws IOException, InterruptedException {
        return client.send(request, HttpResponse.BodyHandlers.ofString());
    }
}
