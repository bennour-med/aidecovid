package com.tww.aidecovid.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

    @Entity
    @Table(name="roles")
    public class Role {
        @Id
        @GeneratedValue(strategy= GenerationType.AUTO)
        private Long id;
        private String role;

        @ManyToMany
        @JoinTable(
                name = "user_role",
                joinColumns = @JoinColumn(name = "role_id"),
                inverseJoinColumns = @JoinColumn(name = "user_id"))
        private List<Member> members = new ArrayList<>();

        protected Role() {	}

        public Role(String role) {
            super();
            this.role = role;
        }

        public Long getId() {
            return id;
        }

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }

        public List<Member> getUsers() {
            return members;
        }

        public Role addUser(Member user) {
            if(!this.members.contains(user)) {
                this.members.add(user);
                user.addRole(this);
            }

            return this;
        }

        public Role removeUser(Member user) {
            if(this.members.contains(user)) {
                this.members.remove(user);
                user.getRoles().remove(this);
            }

            return this;
        }

        @Override
        public String toString() {
            return "Role [id=" + id + ", role=" + role + "]";
        }


}
