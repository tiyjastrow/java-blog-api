package com.theironyard.controllers;

import com.theironyard.entities.User;
import com.theironyard.serializers.RootSerializer;
import com.theironyard.parsers.RootParser;
import com.theironyard.repositories.UserRepository;
import com.theironyard.serializers.UserSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * Created by johnjastrow on 4/10/17.
 */
@RestController
public class UserController {
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    UserRepository users;

    RootSerializer rootSerializer = new RootSerializer();
    UserSerializer userSerializer = new UserSerializer();

    // LOGIN is already provided by Spring Security!!!
//    @RequestMapping(path = "/login", method = RequestMethod.POST)
//    public Map<String, Object> login(@RequestParam String username){
//        User user = users.findByUsername(username);
//        return rootSerializer.serializeOne(
//                "/login",
//                user,
//                userSerializer);
//    }

    @RequestMapping(path = "/users", method = RequestMethod.GET)
    public Map<String, Object> getAll(){
        List<User> userList = users.findAll();
        return rootSerializer.serializeMany(
                "/users",
                userList,
                userSerializer);
    }

    @RequestMapping(path = "/users", method = RequestMethod.POST)
    public Map<String, Object> createUser(@RequestBody RootParser<User> parser, HttpServletResponse response) {
        User user = parser.getData().getEntity();

        // Encrypt Password
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        users.save(user);  // TODO: put in try-catch

        return rootSerializer.serializeOne(
                "/users/" + user.getId(),
                user,
                userSerializer);
    }

    @RequestMapping(path = "/users/current", method = RequestMethod.GET)
    public Map<String, Object> currentUser() {
        // Get the Spring Authentication object from the JWT token
        Authentication u = SecurityContextHolder.getContext().getAuthentication();
        User user = users.findByUsername(u.getName());

        return rootSerializer.serializeOne(
                "/users/" + user.getId(),
                user,
                userSerializer);
    }
}
