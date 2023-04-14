package it.digitazon.musicfy.presentation.controllers;

import it.digitazon.musicfy.persistence.entities.Song;
import it.digitazon.musicfy.persistence.entities.Type;
import it.digitazon.musicfy.presentation.dto.SongDTO;
import it.digitazon.musicfy.presentation.dto.TypeDTO;
import it.digitazon.musicfy.services.TypeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/types")
public class TypeController {

    @Autowired
    private TypeService typeService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public List<TypeDTO> getTypes(){
        return typeService.getAll()
                .stream()
                .map(this::convertToDTO)
                .toList();
    }

    @GetMapping("/{id}")
    public TypeDTO getTypeById(@PathVariable long id){
        return convertToDTO(typeService.getById(id));
    }
     @PostMapping
    public TypeDTO createType(@RequestBody TypeDTO newType){
        Type type = convertToEntity(newType);
        type = typeService.create(type);

        return  convertToDTO(type);
    }

    @PutMapping("/{id}")
    public TypeDTO updateType(@PathVariable long id,@RequestBody TypeDTO updateType){
       Type type = convertToEntity(updateType);
       type = typeService.update(id, type);

        return convertToDTO(type);
    }
    @DeleteMapping("/{id}")
    public TypeDTO deleteType(@PathVariable long id){
        return convertToDTO(typeService.delete(id));

    }
    @GetMapping("/{id}/songs")
    public List<SongDTO> getSongs (@PathVariable long id) {
        Type type = typeService.getById(id);


        return type.getSongs()
                .stream()
                .map(this::convertToSongDTO)
                .toList();
    }

    private TypeDTO convertToDTO (Type type){
        return modelMapper.map(type, TypeDTO.class);
    }

    private Type convertToEntity (TypeDTO dto){
        return modelMapper.map(dto,Type.class);
    }

    private SongDTO convertToSongDTO(Song song) {
        return modelMapper.map(song, SongDTO.class);
    }

}
