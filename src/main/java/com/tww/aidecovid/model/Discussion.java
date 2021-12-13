package com.tww.aidecovid.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="discussions")
public class Discussion {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String tilte;
    private Date created;

    @OneToMany(targetEntity= Message.class, mappedBy="discussion")
    private List<Message> messages = new ArrayList<>();

    @OneToOne(mappedBy = "discussion")
    private Prestation prestation;

    public void addMessage(Message message) {
        messages.add(message);
    }
}

