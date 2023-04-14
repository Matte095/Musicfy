package it.digitazon.musicfy.services;

import it.digitazon.musicfy.persistence.entities.Song;
import it.digitazon.musicfy.presentation.dto.SongDTO;
import it.digitazon.musicfy.presentation.dto.TypeDTO;
import it.digitazon.musicfy.persistence.entities.Type;
import it.digitazon.musicfy.persistence.repositories.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TypeService {

    @Autowired
    private TypeRepository typeRepository;
    public List<Type> getAll() {

        return typeRepository.findAll();
    }
    public Type getById(long id) {
        Optional<Type> optionalType = typeRepository.findById(id);
        if (optionalType.isEmpty()) {
            throw new IllegalStateException("Type not found");
        }
        return optionalType.get();
    }
    public Type create(Type newType) {

        newType = typeRepository.save(newType); // Salva l'entity e il metodo mi restituisce istanza con id generato
        return newType; //restituisco al controller il DTO con id assegnato
    }
    public Type update(long id, Type updateType) {
        Optional<Type> optionalType = typeRepository.findById(id); //cerco entità da aggiornare tramite id

        if (optionalType.isEmpty()) {                                 // se non esiste restituisco errore
            throw new IllegalStateException("Entity not found");
        }
        Type entityToUpdate = optionalType.get();   //se esiste la prendo dal optional
        entityToUpdate.setName(updateType.getName()); // cambio i valori dei campi

        entityToUpdate = typeRepository.save(entityToUpdate);  // salvo entità nel database

        updateType.setId(entityToUpdate.getId());  //valorizzo id del DTO
        return updateType;                       // restituisco il DTO aggiornato
    }
    public Type delete(long id){
        Optional<Type> optionalType = typeRepository.findById(id);

        if(optionalType.isEmpty()){
            throw new IllegalStateException("Entity not found");
        }
        Type entityToDelete = optionalType.get();

        typeRepository.delete(entityToDelete);

        return entityToDelete;
    }

}







