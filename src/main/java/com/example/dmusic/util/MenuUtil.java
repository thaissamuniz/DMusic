package com.example.dmusic.util;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MenuUtil {
    public static void menu(){
		log.info("*=*=*=*==*=*=*=*=*=*=*=*=*==*=*=*=*=*=*=*=*=*=*=*=*=*=*=*==*=*=*=*=*=*=*=*=*=*=*");
        log.info("=========Seja bem vindo(a) ao DMusic! escolha uma das opções abaixo=============");
		log.info("*=*=*=*==*=*=*=*=*=*=*=*=*==*=*=*=*=*=*=*=*=*=*=*=*=*=*=*==*=*=*=*=*=*=*=*=*=*=*");
		log.info("1. Procurar por uma música.");
		log.info("2. Procurar por um álbum.");
		log.info("3. Procurar por um artista ou banda.");
		log.info("3. Criar playlist.");
		log.info("1. Adicionar música à minha playlist.");
		log.info("4. Ver minha playlist.");
		log.info("5. Editar o nome da minha playlist.");
		log.info("7. Remover musica da minha playlist.");
    }
}
