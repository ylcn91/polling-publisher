package com.doksanbir.pollingpublisher.model;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.Set;

@Entity
public class Product extends BaseEntity {

    private String name;
    private Double price;

    @OneToMany(mappedBy = "product")
    private Set<Inventory> inventories;
}

