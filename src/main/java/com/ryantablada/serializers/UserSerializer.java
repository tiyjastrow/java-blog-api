package com.ryantablada.serializers;

import java.util.HashMap;
import java.util.Map;

import com.ryantablada.entities.HasId;
import com.ryantablada.entities.User;

public class UserSerializer extends JsonDataSerializer {

  public String getType() {
    return "users";
  }

  public Map<String, Object> getAttributes(HasId entity) {
    Map<String, Object> result = new HashMap<>();
    User user = (User) entity;

    result.put("username", user.getUsername());

    return result;
  }

  public Map<String, String> getRelationshipUrls() {
    return new HashMap<String, String>();
  }
}
