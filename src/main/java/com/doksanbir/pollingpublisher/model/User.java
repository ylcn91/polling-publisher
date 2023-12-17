package com.doksanbir.pollingpublisher.model;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.Set;

@Entity
public class User extends BaseEntity {

    private String username;
    private String email;

    @OneToMany(mappedBy = "user")
    private Set<Orders> orders;
}
