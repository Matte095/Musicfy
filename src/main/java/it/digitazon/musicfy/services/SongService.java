package it.digitazon.musicfy.services;

import it.digitazon.musicfy.persistence.entities.Song;
import it.digitazon.musicfy.persistence.repositories.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SongService {

    @Autowired
    private SongRepository songRepository;

    public List<Song> getAll(){
        return songRepository.findAll();
    }

    public Song getById(long id){
        Optional<Song> optionalSong = songRepository.findById(id);

        if(optionalSong.isEmpty()){
            throw new IllegalStateException("Song not found");
        }

        return optionalSong.get();
    }

    public  Song create(Song newSong){
        if (newSong.getArtist() == null || newSong.getType()== null){
            throw new IllegalStateException("Artist and Type must not be null");

        }

        newSong = songRepository.save(newSong);
        return newSong;
    }

    public Song update(long id,Song updateSong){
        if (updateSong.getArtist() == null || updateSong.getType() == null ){
            throw new IllegalStateException("Artist and Type must not be null");
        }
        Optional<Song> optionalSong = songRepository.findById(id);
        if(optionalSong.isEmpty()){
            throw new IllegalStateException("Song not found");
        }
        Song entityToUpdate = optionalSong.get();

        updateSong.setId(entityToUpdate.getId());

        updateSong = songRepository.save(updateSong);

        return updateSong;
    }

    public Song delete(long id) {
        Optional<Song> optionalSong = songRepository.findById(id);

        if(optionalSong.isEmpty()){
            throw new IllegalStateException("Song not found");
        }
        Song entityToDelete = optionalSong.get();

        songRepository.delete(entityToDelete);

        return entityToDelete;
    }


}























