package com.tww.aidecovid.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
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
        @NotEmpty(message ="Veuillez introduire votre adresse email")
        private String email;
        private String password;
        private String role;
        private Integer cp;
        private boolean valid;

    @OneToMany(targetEntity= Prestation.class, mappedBy="requester")
    private List<Prestation> resquestedPrestations = new ArrayList<>();

    @OneToMany(targetEntity= Prestation.class, mappedBy="provider")
    private List<Prestation> providedPrestations = new ArrayList<>();

    @ManyToMany(mappedBy = "members", fetch = FetchType.EAGER)
    private List<Role> roles = new ArrayList<>();

    public List<Role> getRoles() {
        return roles;
    }

    public Member addRole(Role role) {
        if(!this.roles.contains(role)) {
            this.roles.add(role);
            role.addUser(this);
        }

        return this;
    }

    public Member removeRole(Role role) {
        if(this.roles.contains(role)) {
            this.roles.remove(role);
            role.getUsers().remove(this);
        }

        return this;
    }

}
