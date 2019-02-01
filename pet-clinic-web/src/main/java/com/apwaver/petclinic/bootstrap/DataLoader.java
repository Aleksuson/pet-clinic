package com.apwaver.petclinic.bootstrap;

import com.apwaver.petclinic.model.Owner;
import com.apwaver.petclinic.model.Vet;
import com.apwaver.petclinic.services.OwnerService;
import com.apwaver.petclinic.services.VetService;
import com.apwaver.petclinic.services.map.OwnerServiceMap;
import com.apwaver.petclinic.services.map.VetServiceMap;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {


    private final OwnerService ownerService;
    private final VetService vetService;

    public DataLoader() {
        ownerService = new OwnerServiceMap();
        vetService = new VetServiceMap();
    }

    @Override
    public void run(String... args) throws Exception {

        Owner owner1 = new Owner();
        owner1.setId(1L);
        owner1.setFirstName("Michael");
        owner1.setLastName("Weston");

        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner1.setId(2L);
        owner1.setFirstName("Fiona");
        owner1.setLastName("Glenanne");

        ownerService.save(owner2);

        System.out.println("Loaded Owners...");

        Vet vet1 = new Vet();
        vet1.setId(1L);
        vet1.setFirstName("Sam");
        vet1.setLastName("Axe");

        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet1.setId(2L);
        vet1.setFirstName("Tim");
        vet1.setLastName("Newton");

        vetService.save(vet2);

        System.out.println("Loaded Vets...");


    }
}