package com.ryantablada.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.ryantablada.entities.User;
import com.ryantablada.parsers.RootParser;
import com.ryantablada.repositories.UserRepository;
import com.ryantablada.serializers.UserSerializer;
import com.ryantablada.serializers.RootSerializer;

@RestController
@CrossOrigin(origins = "*")
public class UserController {

  RootSerializer rootSerializer;
  UserSerializer userSerializer;


  @Autowired
  private BCryptPasswordEncoder bCryptPasswordEncoder;

  public UserController() {
    rootSerializer = new RootSerializer();
    userSerializer = new UserSerializer();
  }

  @Autowired
  UserRepository users;

  @RequestMapping(path = "/users", method = RequestMethod.GET)
  public Map<String, Object> findAllUser() {
    Iterable<User> results = users.findAll();

    return rootSerializer.serializeMany("/users", results, userSerializer);
  }

  @RequestMapping(path = "/users/{id}", method = RequestMethod.GET)
  public Map<String, Object> findOneUser(@PathVariable("id") String id) {
    User user = users.findOne(id);

    return rootSerializer.serializeOne("/users/" + user.getId(), user, userSerializer);
  }

  @RequestMapping(path = "/users/{id}", method = RequestMethod.DELETE)
  public void deleteUser(@PathVariable("id") String id, HttpServletResponse response) {
    users.delete(id);

    response.setStatus(204);
  }

  @RequestMapping(path = "/users/current", method = RequestMethod.GET)
  public Map<String, Object> currentUser() {
    // Get the Spring Authentication object from the JWT token
    Authentication u = SecurityContextHolder.getContext().getAuthentication();

    // Find the logged in user from the database
    User user = users.findByUsername(u.getName());


    return rootSerializer.serializeOne(
      "/users/" + user.getId(),
      user,
      userSerializer);
  }

  @RequestMapping(path = "/users", method = RequestMethod.POST)
  public Map<String, Object> storeUser(@RequestBody RootParser<User> parser) {
    User user = parser.getData().getEntity();

    // Encrypt Password
    user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
    users.save(user);

    return rootSerializer.serializeOne(
      "/users/" + user.getId(),
      user,
      userSerializer);
  }
}
