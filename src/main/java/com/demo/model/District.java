package com.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Getter
@Setter
public class District {
    @Id
    @GeneratedValue
    long id;
    String districtName;
    @JsonIgnore
    @OneToMany(mappedBy = "district")
    List<Offer> offers;
    @JsonIgnore
    @OneToMany
    List<User> users;
}
