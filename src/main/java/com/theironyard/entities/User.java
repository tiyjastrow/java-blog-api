package com.theironyard.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by johnjastrow on 4/7/17.
 */
@Entity
@Table(name = "users")
public class User implements HasId {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    String id;

    public String getId() {
        return id;
    }

    public void setId(String val) {
        this.id = val;
    }

    @Column
    String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
