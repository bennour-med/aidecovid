package com.tww.aidecovid.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="services")
public class Service {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String description;

    @OneToMany(targetEntity= Prestation.class, mappedBy="service")
    private List<Prestation> prestations = new ArrayList<>();

    public Service(String nom, String description){
        this.nom = nom;
        this.description = description;
    }
}
