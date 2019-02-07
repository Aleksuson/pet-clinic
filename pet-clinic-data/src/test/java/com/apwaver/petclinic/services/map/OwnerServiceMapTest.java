package com.apwaver.petclinic.services.map;

import com.apwaver.petclinic.model.Owner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;


class OwnerServiceMapTest {

    private final String lastName = "Hobson";
    private OwnerServiceMap ownerServiceMap;

    private final Long ownerId = 1L;


    @BeforeEach
    void setUp() {
        ownerServiceMap = new OwnerServiceMap(new PetTypeServiceMap(), new PetServiceMap());

        Owner owner =ownerServiceMap.save(Owner.builder().id(ownerId).lastName(lastName).build());
        System.out.println(owner.toString());
    }

    @Test
    void findAll() {
        Set<Owner> ownerSet = ownerServiceMap.findAll();

        assertEquals(1, ownerSet.size());

    }

    @Test
    void delete() {

        ownerServiceMap.delete(ownerServiceMap.findById(ownerId));

        assertEquals(0,ownerServiceMap.findAll().size());
    }

    @Test
    void saveExistingId() {
        Long id = 2L;

        Owner owner2 = Owner.builder().id(id).build();

        Owner savedOwner = ownerServiceMap.save(owner2);

        assertEquals(id,savedOwner.getId());

    }

    @Test
    void saveNoId() {

        Owner ownerNoId = Owner.builder().build();

        Owner savedOwner = ownerServiceMap.save(ownerNoId);

        assertNotNull(savedOwner);
        assertNotNull(savedOwner.getId());
    }

    @Test
    void deleteById() {

        ownerServiceMap.deleteById(ownerId);

        assertEquals(0,ownerServiceMap.findAll().size());
    }

    @Test
    void findById() {
        Owner owner = ownerServiceMap.findById(ownerId);
        assertEquals(ownerId,owner.getId());
    }

    @Test
    void findByLastName() {
        Owner owner = ownerServiceMap.findByLastName(lastName);

        assertNotNull(owner);

        assertEquals(lastName,owner.getLastName());
    }

    @Test
    void findByLastNameNotFound() {
        Owner owner = ownerServiceMap.findByLastName("foo");

        assertNull(owner);

    }


}