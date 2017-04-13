package com.theironyard.serializers;

import java.util.HashMap;
import java.util.Map;

import com.theironyard.entities.HasId;
import com.theironyard.entities.Post;

public class PostSerializer extends JsonDataSerializer {
  
  public String getType() {
    return "posts";
  }

  public Map<String, Object> getAttributes(HasId entity) {
    Map<String, Object> result = new HashMap<>();
    Post post = (Post) entity;

    result.put("title", post.getTitle());
    result.put("content", post.getContent());

    return result;
  }

  public Map<String, String> getRelationshipUrls() {
    return new HashMap<String, String>();
  }
}
