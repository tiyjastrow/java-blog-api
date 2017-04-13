package com.theironyard.entities;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "posts")
public class Post implements HasId {
  private static final long serialVersionUID = 1L;    // Note; implementing Serializable correctly is not trivial.
                                                      // See = http://www.javapractices.com/topic/TopicAction.do?Id=45

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
  String title;

  @Column(columnDefinition = "text")
  String content;

  @ManyToOne
  User user;

  @Transient            // not meant to be persisted
  @JsonIgnore           // not meant to be shared with Client
  String someJunkData;

  // use  @Transient  when the field is not to be persisted

  public void setUser(User u) {
    this.user = u;
  }

  public void setUser(String id) {
    User user = new User();
    user.setId(id);

    this.setUser(user);
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
