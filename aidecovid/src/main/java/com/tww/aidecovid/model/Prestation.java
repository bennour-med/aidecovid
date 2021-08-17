package com.tww.aidecovid.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

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
    @JoinColumn(name = "discussion_id")
    private Discussion discussion;

    @ManyToOne
    @JoinColumn(name = "requester_id", nullable = false)
    private Member requester;

    @ManyToOne
    @JoinColumn(name = "provider_id", nullable = false)
    private Member provider;

}


