package com.ryantablada.entities;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "users")
public class User implements HasId {
  private static final long serialVersionUID = 1L;

  @Column
  String username;

  @Column
  String password;

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

  public String getUsername() {
    return this.username;
  }

  public void setUsername(String val) {
    this.username = val;
  }

  public String getPassword() {
    return this.password;
  }

  public void setPassword(String val) {
    this.password = val;
  }
}
