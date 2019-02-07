package com.apwaver.petclinic.services.springDataJpa;

import com.apwaver.petclinic.model.Owner;
import com.apwaver.petclinic.repositories.OwnerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class OwnerSDJpaServiceTest {

    public static final String LAST_NAME = "Hobson";
    @InjectMocks
    OwnerSDJpaService service;

    @Mock
    OwnerRepository ownerRepository;

    private Set<Owner> ownerSet;
    private Owner returnOwner;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        returnOwner = Owner.builder().id(1L).lastName(LAST_NAME).build();

        ownerSet = new HashSet<>();
        Owner one = Owner.builder().id(1L).lastName(LAST_NAME).build();
        Owner two = Owner.builder().id(2L).lastName("Tim").build();
        ownerSet.add(one);
        ownerSet.add(two);
    }

    @Test
    void findByLastName() {

        when(ownerRepository.findByLastName(eq(LAST_NAME))).thenReturn(Owner.builder().id(1L).lastName(LAST_NAME).build());
        Owner owner = service.findByLastName(LAST_NAME);

        assertEquals("Hobson",owner.getLastName());

        verify(ownerRepository).findByLastName(any());
    }

    @Test
    void findAll() {
        when(ownerRepository.findAll()).thenReturn(ownerSet);

        Set<Owner> owners = service.findAll();

        assertEquals(ownerSet,owners);
    }

    @Test
    void findById() {
        Optional<Owner> optionalOwner= Optional.of(returnOwner);
        when(ownerRepository.findById(eq(1L))).thenReturn(optionalOwner);
        Owner owner = service.findById(1L);
        assertEquals(returnOwner,owner);
    }

    @Test
    void save() {
        Owner ownerToSave = returnOwner;
        when(ownerRepository.save(any())).thenReturn(returnOwner);
        Owner savedOwner = service.save(ownerToSave);
        assertNotNull(savedOwner);
    }

    @Test
    void delete() {
        service.delete(returnOwner);

        verify(ownerRepository).delete(any());
    }

    @Test
    void deleteById() {
        service.deleteById(1L);

        verify(ownerRepository).deleteById(any());
    }
}