package com.apwaver.petclinic.repositories;

import com.apwaver.petclinic.model.Vet;
import org.springframework.data.repository.CrudRepository;

public interface VetRepository extends CrudRepository<Vet,Long > {
}
