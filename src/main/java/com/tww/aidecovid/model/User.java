package com.tww.aidecovid.model;

import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message ="Veuillez introduire un login")
    @Size(min = 5, max = 30, message = "Doit contenir minimum 5 caractères")
    private String login;
    @NotEmpty
    private String password;
    @NotEmpty
    private String firstname;
    @NotEmpty
    private String lastname;
    @NotEmpty
    @Email
    private String email;
    private String langue;

    public User(String firstname, String lastname, String login, String email, String password) {
        this.login = login;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
    }

    @Column(columnDefinition = "varchar(20) default 'member'", insertable = false)
    private String role;

    @OneToMany(targetEntity= Prestation.class, mappedBy="requester")
    private List<Prestation> resquestedPrestations = new ArrayList<>();

    @OneToMany(targetEntity= Prestation.class, mappedBy="provider")
    private List<Prestation> providedPrestations = new ArrayList<>();

}
