package it.digitazon.musicfy.services;

import it.digitazon.musicfy.persistence.entities.Artist;
import it.digitazon.musicfy.persistence.repositories.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArtistService {

    @Autowired
    private ArtistRepository artistRepository;
    public List<Artist> getAll(){
        return artistRepository.findAll();
    }
    public Artist getById(long id){
        Optional<Artist> optionalArtist = artistRepository.findById(id);

        if ( optionalArtist.isEmpty()){
            throw new IllegalStateException("Artist not found");

        }
        return  optionalArtist.get();
    }
    public Artist create(Artist newArtist){

        newArtist = artistRepository.save(newArtist);

        return newArtist;
    }
    public Artist update(long id, Artist updateArtist){
        Optional<Artist> optionalArtist = artistRepository.findById(id);

        if(optionalArtist.isEmpty()){
            throw new IllegalStateException("Artist not found");
        }
        Artist entityToUpdate = optionalArtist.get();

        updateArtist.setId(entityToUpdate.getId());
        updateArtist = artistRepository.save(updateArtist);

        return updateArtist;
    }
    public Artist delete(long id){
        Optional<Artist> optionalArtist = artistRepository.findById(id);

        if (optionalArtist.isEmpty()){
            throw new IllegalStateException("Artist not found");
        }
        Artist entityToDelete = optionalArtist.get();

        artistRepository.delete(entityToDelete);

        return entityToDelete;
    }
}
