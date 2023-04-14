package it.digitazon.musicfy.persistence.repositories;

import it.digitazon.musicfy.persistence.entities.Type;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TypeRepository extends JpaRepository<Type,Long> {
}
