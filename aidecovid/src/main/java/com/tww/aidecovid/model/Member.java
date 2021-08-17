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
    @Table(name="members")
    public class Member {
        @Id
        @GeneratedValue(strategy= GenerationType.IDENTITY)
        private Long id;
        private String nom;
        private String prenom;
        private Integer cp;
        private boolean valid;

    @OneToMany(targetEntity= Prestation.class, mappedBy="requester")
    private List<Prestation> resquestedPrestations = new ArrayList<>();

    @OneToMany(targetEntity= Prestation.class, mappedBy="provider")
    private List<Prestation> providedPrestations = new ArrayList<>();
}
