package com.example.dmusic;

import com.example.dmusic.model.*;
import com.example.dmusic.service.*;
import com.example.dmusic.util.MenuUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
@Slf4j
public class DmusicApplication {

    public static void main(String[] args) {
        SpringApplication.run(DmusicApplication.class, args);
        MenuUtil.menu();
        Scanner scanner = new Scanner(System.in);
        TrackService trackService = new TrackService();
        int option = scanner.nextInt();
        switch (option) {
            case 1: {
                log.info("Digite o nome da música que você deseja procurar:");
                String musicName = scanner.next();
                TrackList musics = trackService.getTrack(musicName);

                musics.getData().forEach(music -> {
                    log.info("ID: " + music.getId());
                    log.info("MÚSICA: " + music.getTitle());
                    log.info("ARTISTA: " + music.getArtist().getName());
                    log.info("ÁLBUM: " + music.getAlbum().getTitle());
                    log.info("===========================================");
                });
                break;
            }
            case 2: {
                log.info("Defina um nome para sua playlist");
                String playlistName = scanner.next();
                Playlist playlist = new Playlist();
                playlist.setName(playlistName);
                break;
            }
            case 3: {
                log.info("Informe o ID da música que você deseja adicionar a playlist");
                String id = scanner.next();

            }
        }
//
//		AlbumService albumService = new AlbumService();
//		ArtistListService artistListService = new ArtistListService();
//		TrackService trackService = new TrackService();


//		for(Track track : trackList.getData()) {
//			log.info("música: " + track.getTitle());
//			log.info("artista: " + track.getArtist().getName());
//			log.info("album: " + track.getAlbum().getTitle());
//			log.info("duração em minutos: ");
//			log.info("==================");
//		}


//		for(Artist art : artist.getData()) {
//			log.info("Nome do Artista: " + art.getName());
//			log.info("id: " + art.getId());
//			log.info("fãs: " + art.getNb_fan());
//			log.info("==================");
//		}
//
//		for(Album alb : albumList.getData()) {
//			log.info("Nome do Album: " + alb.getTitle());
//			log.info("Artista: " + alb.getArtist().getName());
//			log.info("==================");
//		}
    }
}
