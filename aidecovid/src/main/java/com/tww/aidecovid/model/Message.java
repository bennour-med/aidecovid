package com.tww.aidecovid.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="messages")
public class Message {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String text;
    private Date created;


    @ManyToOne
    @JoinColumn(name="discussion_id", nullable=false)
    private Discussion discussion;

}
