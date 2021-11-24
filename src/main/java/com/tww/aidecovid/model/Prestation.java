package com.tww.aidecovid.model;

import com.tww.aidecovid.statics.CodePostal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="prestations")
public class Prestation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "service_id", nullable = false)
    private Service service;

    @OneToOne
    @JoinColumn(name = "discussion_id", nullable = true)
    private Discussion discussion;

    @ManyToOne
    @JoinColumn(name = "requester_id", nullable = true)
    private User requester;

    @ManyToOne
    @JoinColumn(name = "provider_id", nullable = false)
    private User provider;

    private Date date;
    private String cpList;
    private String status;
}


