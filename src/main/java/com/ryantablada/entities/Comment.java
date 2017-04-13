package com.ryantablada.entities;

import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class Comment implements HasId {
  private static final long serialVersionUID = 1L;

  @ManyToOne
  Post post;

  @Column(columnDefinition = "text")
  String content;

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

  public Post getPost() {
    return post;
  }

  public void setPost(Post val) {
    this.post = val;
  }

  public void setPost(String id) {
    Post post = new Post();
    post.setId(id);

    this.setPost(post);
  }

  public String getContent() {
    return this.content;
  }

  public void setContent(String val) {
    this.content = val;
  }
}
