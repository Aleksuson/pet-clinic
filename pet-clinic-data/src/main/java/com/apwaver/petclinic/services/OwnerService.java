package com.apwaver.petclinic.services;

import com.apwaver.petclinic.model.Owner;


public interface OwnerService extends CrudService<Owner,Long>{

    Owner findByLastName(String lastName);

}
