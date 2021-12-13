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
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
    private Date created;


    @ManyToOne
    @JoinColumn(name="discussion_id", nullable=false)
    private Discussion discussion;

    @Override
    public String toString() {
        StringBuilder value = new StringBuilder(user.getFirstname());
        value.append(" ("); value.append(created); value.append("):");
        value.append(text); value.append("\n");
        return value.toString();
    }
}
