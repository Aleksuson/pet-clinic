package com.apwaver.petclinic.controller;

import com.apwaver.petclinic.model.Owner;
import com.apwaver.petclinic.services.OwnerService;
import com.apwaver.petclinic.services.map.PetServiceMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.*;

import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@ExtendWith(MockitoExtension.class)
class OwnerControllerTest {

    @InjectMocks
    OwnerController ownerController;

    @Mock
    OwnerService ownerService;

    private Set<Owner> ownerSet;

    private MockMvc mockMvc;

    private static final Long TEST_OWNER_ID = 1L;

    private Owner alex;

    @BeforeEach
    void setUp() {
        ownerSet = new HashSet<>();
        ownerSet = new HashSet<>();
        Owner one = Owner.builder().id(1L).lastName("Branson").build();
        Owner two = Owner.builder().id(2L).lastName("Gates").build();
        ownerSet.add(one);
        ownerSet.add(two);

        mockMvc = MockMvcBuilders.standaloneSetup(ownerController).build();

        alex = new Owner();
        alex.setId(TEST_OWNER_ID);
        alex.setFirstName("Alex");
        alex.setLastName("Newborn");
        alex.setAddress("32 Shire St.");
        alex.setCity("York");
        alex.setTelephone("73545345");

    }

    @Test
    void findOwners() throws Exception {
        mockMvc.perform(get("/owners/find"))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/findOwners"))
                .andExpect(model().attributeExists("owner"));

        verifyZeroInteractions(ownerService);
    }

    @Test
    void processFindFormReturnMany() throws Exception {
        when(ownerService.findAllByLastNameLike(anyString())).thenReturn(new ArrayList<>(ownerSet));

        mockMvc.perform(get("/owners"))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/ownerList"))
                .andExpect(model().attribute("selections", hasSize(2)));
    }

    @Test
    void processFindFormReturnOne() throws Exception {
        when(ownerService.findAllByLastNameLike(anyString())).thenReturn(Arrays.asList(alex));

        mockMvc.perform(get("/owners"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owners/1"));

    }

    @Test
    void showOwnerTest() throws Exception {

        given(ownerService.findById(TEST_OWNER_ID)).willReturn(alex);

        mockMvc.perform(get("/owners/{ownerID}",1L))
                .andExpect(status().isOk())
                .andExpect(model().attribute("owner", hasProperty("firstName",is("Alex"))))
                .andExpect(model().attribute("owner", hasProperty("lastName",is("Newborn"))))
                .andExpect(model().attribute("owner", hasProperty("address",is("32 Shire St."))))
                .andExpect(model().attribute("owner", hasProperty("city",is("York"))))
                .andExpect(model().attribute("owner", hasProperty("telephone",is("73545345"))))
                .andExpect(view().name("owners/ownerDetails"));
    }

    @Test
    void initCreationForm() throws Exception {
        mockMvc.perform(get("/owners/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/createOrUpdateOwnerForm"))
                .andExpect(model().attributeExists("owner"));

        verifyZeroInteractions(ownerService);
    }

    @Test
    void processCreationForm() throws Exception {
        when(ownerService.save(any())).thenReturn(alex);

        mockMvc.perform(post("/owners/new"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owners/1"))
                .andExpect(model().attributeExists("owner"));

        verify(ownerService).save(any());
    }

    @Test
    void initUpdateOwnerForm() throws Exception {
        when(ownerService.findById(anyLong())).thenReturn(alex);

        mockMvc.perform(get("/owners/1/edit"))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/createOrUpdateOwnerForm"))
                .andExpect(model().attributeExists("owner"));

        verifyZeroInteractions(ownerService);
    }

    @Test
    void processUpdateOwnerForm() throws Exception {
        when(ownerService.save(any())).thenReturn(alex);

        mockMvc.perform(post("/owners/1/edit"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owners/1"))
                .andExpect(model().attributeExists("owner"));

        verify(ownerService).save(any());
    }
}

