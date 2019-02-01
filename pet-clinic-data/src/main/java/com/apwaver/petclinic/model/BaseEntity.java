package com.apwaver.petclinic.model;

import java.io.Serializable;
import java.util.Objects;

public class BaseEntity implements Serializable {

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
