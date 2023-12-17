package com.doksanbir.pollingpublisher.model;

import jakarta.persistence.Entity;

@Entity
public class Location extends BaseEntity {

    private String name;
    private String description;

}
