package com.apwaver.petclinic.services;

import com.apwaver.petclinic.model.Vet;

import java.util.Set;

public interface VetService {

    Vet findByLastName(String lastName);

    Vet findById(Long id);

    Vet save(Vet Vet);

    Set<Vet> findAll();
}
