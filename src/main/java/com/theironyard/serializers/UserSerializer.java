package com.theironyard.serializers;

import com.theironyard.entities.HasId;
import com.theironyard.entities.User;

import java.util.HashMap;
import java.util.Map;

public class UserSerializer extends JsonDataSerializer {
  
  public String getType() {
    return "users";
  }

  public Map<String, Object> getAttributes(HasId entity) {
    Map<String, Object> result = new HashMap<>();
    User user = (User) entity;

    result.put("email", user.getEmail());

    return result;
  }

  public Map<String, String> getRelationshipUrls() {
    return new HashMap<String, String>();
  }
}
