package com.demo.model;

import com.demo.Authentication.UserRole;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
public class User {//(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
    @Id
    @GeneratedValue
    long id;
    private String firstName;
    private String lastName;
    @JsonIgnore
    private String password;
    //@JsonIgnore
    @ManyToOne
    private District district;
    private String contactNumber;
    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(name = "user_roles",
    joinColumns = {@JoinColumn(name = "user_id",referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id",referencedColumnName = "id")})
    private List<UserRole> roles;
    private String email;
    @JsonIgnore
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Offer> offers;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Offer> reportedOffers;
    String username;

}
