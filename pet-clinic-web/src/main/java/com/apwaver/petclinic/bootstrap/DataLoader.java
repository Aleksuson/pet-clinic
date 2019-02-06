package com.apwaver.petclinic.bootstrap;

import com.apwaver.petclinic.model.*;
import com.apwaver.petclinic.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {


    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final SpecialityService specialitiesService;
    private final VisitService visitService;

    @Autowired
    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService,
                      SpecialityService specialitiesService, VisitService visitService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialitiesService = specialitiesService;
        this.visitService = visitService;
    }

    @Override
    public void run(String... args) throws Exception {

        int count = petTypeService.findAll().size();

        if(count == 0) {
            LoadData();
        }

    }

    private void LoadData() {
        // PetTypes Initialization

        PetType dog = new PetType();
        dog.setName("Dog");
        PetType savedDogPetType = petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("Cat");
        PetType savedCatPetType = petTypeService.save(cat);

        // Owners Initialization

        Owner owner1 = new Owner();
        owner1.setFirstName("Michael");
        owner1.setLastName("Weston");
        owner1.setAddress("Buckingham St. 14");
        owner1.setCity("London");
        owner1.setTelephone("832-323-323");


        Pet mikesPet = new Pet();
        mikesPet.setPetType(savedDogPetType);
        mikesPet.setOwner(owner1);
        mikesPet.setBirthDate(LocalDate.now());
        mikesPet.setName("Rosco");
        owner1.getPets().add(mikesPet);

        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("Fiona");
        owner2.setLastName("Glenanne");
        owner2.setAddress("Truckhammer St. 23/12");
        owner2.setCity("Manchester");
        owner2.setTelephone("633-423-425");

        Pet fionaPet = new Pet();
        fionaPet.setPetType(savedCatPetType);
        fionaPet.setOwner(owner2);
        fionaPet.setBirthDate(LocalDate.now());
        fionaPet.setName("Mruczek");
        owner2.getPets().add(fionaPet);

        ownerService.save(owner2);

        Visit catVisit = new Visit();
        catVisit.setDate(LocalDate.now());
        catVisit.setPet(fionaPet);
        catVisit.setDescription("Sneezy Cat");

        visitService.save(catVisit);

        Owner owner3 = new Owner();
        owner3.setFirstName("Ted");
        owner3.setLastName("Bundy");
        owner3.setAddress("New Ark St. 3/2");
        owner3.setCity("York");
        owner3.setTelephone("231-412-333");

        Pet tedPet = new Pet();
        tedPet.setPetType(savedDogPetType);
        tedPet.setOwner(owner3);
        tedPet.setBirthDate(LocalDate.now());
        tedPet.setName("Mixi");
        owner2.getPets().add(tedPet);

        ownerService.save(owner3);
        System.out.println("Loaded Owners...");

        // Vet Initialization

        Specialty radiology = new Specialty();
        radiology.setDescription("Radiology");
        Specialty savedRadiology = specialitiesService.save(radiology);

        Specialty surgery = new Specialty();
        surgery.setDescription("Surgery");
        Specialty savedSurgery = specialitiesService.save(surgery);

        Specialty dentistry = new Specialty();
        dentistry.setDescription("Dentistry");
        Specialty savedDentistry = specialitiesService.save(dentistry);

        Vet vet1 = new Vet();

        vet1.setFirstName("Sam");
        vet1.setLastName("Axe");
        vet1.getSpecialties().add(savedSurgery);

        vetService.save(vet1);

        Vet vet2 = new Vet();

        vet2.setFirstName("Tim");
        vet2.setLastName("Newton");
        vet2.getSpecialties().add(savedRadiology);

        vetService.save(vet2);

        System.out.println("Loaded Vets...");
    }
}
