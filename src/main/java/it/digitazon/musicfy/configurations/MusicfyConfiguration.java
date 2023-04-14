package it.digitazon.musicfy.configurations;

import it.digitazon.musicfy.persistence.entities.Artist;
import it.digitazon.musicfy.presentation.dto.ArtistDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class MusicfyConfiguration {
    @Bean
    public ModelMapper modelMapper() {

        //    @Bean
//    public ModelMapper modelMapper() {
//        return new ModelMapper();
//    }
//
        // Creo l'istanza del model mapper che verrà restituita ai controller in autowired
        ModelMapper modelMapper = new ModelMapper();

        // Inizializza il property mapper per configurare il map di propietà "personalizzate"
        TypeMap<Artist, ArtistDTO> artistToDTOmapper = modelMapper.createTypeMap(Artist.class, ArtistDTO.class);

        // Definisco il mapping, passando al metodo addMapping
        // - il primo parametro il metodo "get.." della sorgente (Artist::getBirthDate)
        // - il secondo parametro il metodo "set.." della destinazione (ArtistDTO::convertDateToString)
        artistToDTOmapper.addMapping(Artist::getBirthDate, ArtistDTO::convertDateToString);
        TypeMap<ArtistDTO, Artist> DTOToArtistMapper = modelMapper.createTypeMap(ArtistDTO.class, Artist.class);
        DTOToArtistMapper.addMapping(ArtistDTO::convertBirthDate,Artist::setBirthDate);
        // restituisco il modelMapper configurato
        return modelMapper;
    }
}

