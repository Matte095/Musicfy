package it.digitazon.musicfy.persistence.repositories;

import it.digitazon.musicfy.persistence.entities.Song;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SongRepository extends JpaRepository<Song,Long> {
}
