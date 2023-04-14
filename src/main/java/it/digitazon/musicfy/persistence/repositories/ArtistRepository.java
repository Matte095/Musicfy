package it.digitazon.musicfy.persistence.repositories;

import it.digitazon.musicfy.persistence.entities.Artist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtistRepository extends JpaRepository<Artist,Long> {
}
