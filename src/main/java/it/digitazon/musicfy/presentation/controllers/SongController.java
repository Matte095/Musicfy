package it.digitazon.musicfy.presentation.controllers;

import it.digitazon.musicfy.persistence.entities.Artist;
import it.digitazon.musicfy.persistence.entities.Song;
import it.digitazon.musicfy.persistence.entities.Type;
import it.digitazon.musicfy.presentation.dto.ArtistDTO;
import it.digitazon.musicfy.presentation.dto.SongDTO;
import it.digitazon.musicfy.presentation.dto.TypeDTO;
import it.digitazon.musicfy.services.ArtistService;
import it.digitazon.musicfy.services.SongService;
import it.digitazon.musicfy.services.TypeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/songs")
public class SongController {

    @Autowired
    private SongService songService;
    @Autowired
    private TypeService typeService;
    @Autowired
    private ArtistService artistService;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public List<SongDTO> getSongs(){
        return songService.getAll()
                .stream()
                .map(this::convertToDTO)
                .toList();
    }

    @GetMapping("/{id}")
    public SongDTO getSongById(@PathVariable long id){
        return convertToDTO(songService.getById(id));
    }

@PostMapping
public SongDTO createSOng (@RequestBody SongDTO newSong){
        Song song = convertToEntity(newSong);
        song = songService.create(song);

        return convertToDTO(song);
}

@PutMapping ("/{id}")
public SongDTO updateSong (@PathVariable long id, @RequestBody SongDTO updateSong){

        Song song = convertToEntity(updateSong);
        song = songService.update(id,song);

        return  convertToDTO(song);
}


@DeleteMapping("/{id}")
public SongDTO deleteSong (@PathVariable long id){
        return convertToDTO(songService.delete(id));
}


@GetMapping ("/{id}/artist")
public ArtistDTO getArtist (@PathVariable long id) {
        Song song = songService.getById(id);

        return  convertToArtistDTO(song.getArtist());
}


    @GetMapping ("/{id}/type")
    public TypeDTO getType (@PathVariable long id) {
        Song song = songService.getById(id);

        return  convertoToTypeDTO(song.getType());
    }



    private SongDTO convertToDTO(Song song){
        return modelMapper.map(song, SongDTO.class);
    }

    private Song convertToEntity(SongDTO dto){
        Song song = modelMapper.map(dto, Song.class);
        return song;
    }

    private ArtistDTO convertToArtistDTO (Artist artist){
        return modelMapper.map(artist, ArtistDTO.class);
    }

    private TypeDTO convertoToTypeDTO (Type type){
        return modelMapper.map(type, TypeDTO.class);
    }
}
