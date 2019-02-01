package com.apwaver.petclinic.services.map;

import com.apwaver.petclinic.model.Pet;
import com.apwaver.petclinic.services.CrudService;
import com.apwaver.petclinic.services.PetService;

import java.util.Set;

public class PetServiceMap extends AbstractMapService<Pet, Long> implements PetService {
    
    @Override
    public Set<Pet> findAll() {
        return super.findAll();
    }

    @Override
    public void delete(Pet object) {
        super.delete(object);
    }

    @Override
    public Pet save(Pet object) {
        return super.save(object.getId(),object);
    }

    @Override
    public void deleteById(Long id) {
        super.deletById(id);
    }

    @Override
    public Pet findById(Long id) {
        return super.findById(id);
    }
}
