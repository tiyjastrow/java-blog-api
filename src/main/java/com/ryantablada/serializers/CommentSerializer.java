package com.ryantablada.serializers;

import java.util.HashMap;
import java.util.Map;

import com.ryantablada.entities.HasId;
import com.ryantablada.entities.Comment;

public class CommentSerializer extends JsonDataSerializer {
  
  public String getType() {
    return "comments";
  }

  public Map<String, Object> getAttributes(HasId entity) {
    Map<String, Object> result = new HashMap<>();
    Comment post = (Comment) entity;

    result.put("content", post.getContent());

    return result;
  }

  public Map<String, String> getRelationshipUrls() {
    return new HashMap<String, String>() {{
        put("post", "/comments/{id}/post");
    }};
  }
}
