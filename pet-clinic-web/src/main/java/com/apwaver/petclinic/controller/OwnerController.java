package com.apwaver.petclinic.controller;

import com.apwaver.petclinic.model.Owner;
import com.apwaver.petclinic.services.OwnerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Set;

@RequestMapping("/owners")
@Controller
public class OwnerController {

    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @RequestMapping({"","/","/index","/index.html"})
    public String listOwners(Model model){

        Set<Owner> set = ownerService.findAll();
        for(Owner owner: set){
            System.out.println(owner.getFirstName());
        }

        model.addAttribute("owners", ownerService.findAll());
        return "owners/index";
    }
}
