## DMusic

API REST para gerenciar playlists de música.

## Rotas

*   GET /tracks?name=""
    *   Retorna diversas informações das músicas encontradas com o nome passado.
*   GET /playlist
    *   Retorna uma lista com todas as playlists criadas.
*   GET /playlist?name=""&tag=""
    *   Retorna uma lista filtrada pelos parâmetros.
*   POST /playlist
    *   Cria uma nova playlist

```body
{
	“userId”: 1,
	“name”: “nostalgia”,
	“tags”: [“animadas”, “festa”],
	“tracksIds”: [“1509938122”, “3530722”]
}
```

*   PUT /playlist/id
    *   Edita uma playlist já existente.

```body
{
	“name”: “favoritas”
	“tags”: [“animadas”, “festa”],
	“tracksIds”: [“1509938122”, “3530722”]
}
```
*   DELETE /playlist/id
    *   Deleta uma playlist já existente.
