package com.ryantablada.entities;

import java.util.Set;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Post implements HasId {
  private static final long serialVersionUID = 1L;

  @Column
  String title;

  @Column(columnDefinition = "text")
  String content;

  @Id
  @GeneratedValue(generator="system-uuid")
  @GenericGenerator(name="system-uuid", strategy = "uuid")
  String id;

  @OneToMany(mappedBy = "post")
  Set<Comment> comments;

  public String getId() {
    return id;
  }

  public void setId(String val) {
    this.id = val;
  }

  public String getTitle() {
    return this.title;
  }

  public void setTitle(String val) {
    this.title = val;
  }

  public String getContent() {
    return this.content;
  }

  public void setContent(String val) {
    this.content = val;
  }

  public Set<Comment> getComments() {
    return this.comments;
  }
}
