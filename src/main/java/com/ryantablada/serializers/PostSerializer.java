package com.ryantablada.serializers;

import java.util.HashMap;
import java.util.Map;

import com.ryantablada.entities.HasId;
import com.ryantablada.entities.Post;

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
    return new HashMap<String, String>() {{
        put("comments", "/posts/{id}/comments");
    }};
  }
}
