package com.ryantablada.entities;

public class Post implements HasId {
  String title;
  String content;
  Integer id;

  public String getId() {
    return this.id.toString();
  }

  public void setId(Integer val) {
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
}
